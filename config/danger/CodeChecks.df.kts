@file:DependsOn("xyz.pavelkorolev.danger.detekt:plugin:1.2.0")
@file:DependsOn("net.appsynth.danger:danger-kotlin-jacoco:0.2.1")
@file:DependsOn("edu.hm.hafner:coverage-model:0.51.0")

import edu.hm.hafner.coverage.Coverage
import edu.hm.hafner.coverage.Metric
import edu.hm.hafner.coverage.Value
import edu.hm.hafner.coverage.parser.JacocoParser
import edu.hm.hafner.util.FilteredLog
import net.appsynth.danger.JaCoCoPlugin
import net.appsynth.danger.jacoco
import systems.danger.kotlin.danger
import systems.danger.kotlin.fail
import systems.danger.kotlin.markdown
import systems.danger.kotlin.models.git.FilePath
import systems.danger.kotlin.register
import systems.danger.kotlin.warn
import xyz.pavelkorolev.danger.detekt.DetektPlugin
import java.io.File


register plugin DetektPlugin
register plugin JaCoCoPlugin

danger(args) {
    markdown("## Code checks")
    reportDetekt()

    val changedFiles = git.modifiedFiles + git.createdFiles
    reportCoverage(changedFiles)
}

fun reportDetekt() {
    val file = File("build/reports/detekt/detekt.sarif")
    if (!file.exists()) {
        fail(
            """
            ### Detekt
            No detekt report found 🙈
            """.trimIndent(),
        )
        return
    }
    with(DetektPlugin) {
        val report = parse(file)
        val count = report.count
        if (count == 0) {
            markdown(
                """
                ### Detekt
                Good job! Detekt found no violations here! 👏👏👏
                """.trimIndent(),
            )
            return
        }
        fail(
            """
            ### Detekt
            Detekt violations found: **${report.count}** 😱
            Please fix them to proceed. We have a zero-warning policy
            """.trimIndent(),
        )
        report(report)
    }
}

fun reportCoverage(changedFiles: List<FilePath>) {
    val reportFileName = "build/reports/kover/report.xml"
    val refReportFileName = "wiki/ref-report.xml"

    val aggregatedReport = getAggregate(reportFileName)
    val aggregatedReference = getAggregate(refReportFileName)
    fun Double.toCoveragePercent() = "%.2f%%".format(this)
    fun Double.toDiff() = "%+.2f".format(this)
    markdown(
        """
        ### Coverage diff
        | **Class** | **Method** | **Branch** | **Line** | **Instruction** |
        | --------- | ---------- | ---------- | -------- | --------------- |
        ${produceMarkdownRow {
            val currentCoverage = (aggregatedReport.getValue(it) as Coverage).coveredPercentage.toDouble()
            val referenceCoverage = (aggregatedReference.getValue(it) as Coverage).coveredPercentage.toDouble()
            val displayedCoverage = "${currentCoverage.toCoveragePercent()} (${(currentCoverage - referenceCoverage).toDiff()})"
            val trend = when {
                currentCoverage - referenceCoverage > 0 -> ":thumbsup:"
                currentCoverage - referenceCoverage < 0 -> ":thumbsdown:"
                else -> ":v:"
            }
            "$displayedCoverage $trend"
        }}
        
        _Compared to the `main` branch._
        """.trimIndent(),
    )

    jacoco {
        excludePatterns = listOf(
            Regex(""".*/src/.*Test/.*\.kt"""),
            Regex("build-logic/.*"),
        )

        val report = File(reportFileName)
        if (!report.exists()) {
            fail(
                """
                ### Kover
                No coverage report found 🙈
                """.trimIndent(),
            )
            return@jacoco
        }
        parse(report)
        val refReport = File(refReportFileName)
        if (refReport.exists()) {
            reference(refReport)
        } else {
            warn("No reference coverage report found")
        }
        val coverableFiles = getCoverableFiles(reportFileName)
        val changedAndCoverableFiles = changedFiles.filter { changedFile ->
            coverableFiles.any { changedFile.endsWith(it) }
        }
        report(changedAndCoverableFiles)
    }
}

fun getAggregate(fileName: String): Map<Metric, Value> {
    val moduleNode = JacocoParser().parse(File(fileName).reader(), fileName, FilteredLog())
    return moduleNode.aggregateValues().associateBy { it.metric }
}

fun getCoverableFiles(fileName: String): List<FilePath> {
    val moduleNode = JacocoParser().parse(File(fileName).reader(), fileName, FilteredLog())
    return moduleNode.files.toList()
}

fun produceMarkdownRow(format: (Metric) -> String): String {
    return "| " + format(Metric.CLASS) + " | " +
        format(Metric.METHOD) + " | " +
        format(Metric.BRANCH) + " | " +
        format(Metric.LINE) + " | " +
        format(Metric.INSTRUCTION) + " |"
}
