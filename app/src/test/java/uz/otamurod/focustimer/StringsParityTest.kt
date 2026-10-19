package uz.otamurod.focustimer

import java.io.File
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Resource files are plain XML, so a unit test can guard them. This one fails the build
 * when a translation misses a string key, or has a key the default file does not know.
 *
 * Gradle runs unit tests with the module folder (app/) as the working directory.
 */
class StringsParityTest {

    private val resDir = File("src/main/res")

    private fun stringKeys(file: File): Set<String> =
        Regex("""<string\s+name="([^"]+)"""")
            .findAll(file.readText())
            .map { it.groupValues[1] }
            .toSet()

    @Test
    fun defaultStringsExist() {
        val defaults = File(resDir, "values/strings.xml")
        assertTrue("Missing ${defaults.path}", defaults.exists())
        assertTrue("app_name must be defined", "app_name" in stringKeys(defaults))
    }

    @Test
    fun everyTranslationHasExactlyTheDefaultKeys() {
        val defaultKeys = stringKeys(File(resDir, "values/strings.xml"))
        val translations = resDir.listFiles { f ->
            f.isDirectory && f.name.startsWith("values-") && File(f, "strings.xml").exists()
        }.orEmpty()

        assertTrue("Expected at least one translation (values-uz)", translations.isNotEmpty())

        for (folder in translations) {
            val keys = stringKeys(File(folder, "strings.xml"))
            assertEquals("Keys in ${folder.name}/strings.xml differ from values/strings.xml", defaultKeys, keys)
        }
    }
}
