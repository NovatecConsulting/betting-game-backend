package utils

import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import org.skyscreamer.jsonassert.JSONCompare
import org.skyscreamer.jsonassert.JSONCompareMode.NON_EXTENSIBLE

class JsonMatcher(private val expectedJson: String) : TypeSafeMatcher<String>() {

    companion object {
        fun jsonEqualTo(expectedJson: String) = JsonMatcher(expectedJson)
    }

    override fun describeTo(description: Description) {
        description.appendValue(expectedJson)
    }

    override fun matchesSafely(actual: String): Boolean =
        JSONCompare.compareJSON(expectedJson, actual, NON_EXTENSIBLE).passed()
}
