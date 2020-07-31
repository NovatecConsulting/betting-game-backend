package utils.classification

import org.junit.jupiter.api.Tag

/**
 *
 * An integration test is a test integrating one or more components of a
 * system. A component can be a module, a set of classes, a framework or
 * something like a database. It is allowed to fake (mock, stub etc.) parts
 * of the application in order to isolate the part you want to test.
 *
 */
@Retention
@Target(AnnotationTarget.CLASS)
@Tag("integration-test")
annotation class IntegrationTest
