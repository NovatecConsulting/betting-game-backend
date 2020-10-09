package de.novatec.betting.game

import com.tngtech.archunit.base.DescribedPredicate
import com.tngtech.archunit.core.domain.JavaClass
import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import org.junit.jupiter.api.Test
import javax.ws.rs.Path

private const val ALL_PACKAGES: String = "de.novatec.betting.game.."
private const val SERVICE: String = "Service"
private const val REST_CONTROLLER: String = "RestController"
private const val ACCESSOR: String = "Accessor"

class BettingGameArchTest {

    private val mainClasses = loadMainClasses()

    private fun loadMainClasses(): JavaClasses {
        return ClassFileImporter().importPackages(ALL_PACKAGES)
            .that(object: DescribedPredicate<JavaClass>("are no tests") {
                override fun apply(input: JavaClass): Boolean {
                    return !input.name.contains("Test")
                }
            })
    }

    @Test
    fun restControllersShouldEndWithNameRestController() {
        classes().that().areAnnotatedWith(Path::class.java).should()
            .haveSimpleNameEndingWith(REST_CONTROLLER)
            .because("The name of rest controllers should end with 'RestController'.")
            .check(mainClasses)
    }

    @Test
    fun restControllersShouldBeAnnotatedWithPath() {
        classes().that().haveSimpleNameEndingWith(REST_CONTROLLER).should()
            .beAnnotatedWith(Path::class.java)
            .because("Rest controllers should have a path.")
            .check(mainClasses)
    }

    @Test
    fun accessorsShouldEndWithNameAccessor() {
        classes().that().areAnnotatedWith(RegisterRestClient::class.java).should()
            .haveSimpleNameEndingWith(ACCESSOR)
            .because("The name of rest clients should end with 'Accessor'.")
            .check(mainClasses)
    }

    @Test
    fun accessorsShouldBeAnnotatedWithRegisterRestClient() {
        classes().that().haveSimpleNameEndingWith(ACCESSOR).should()
            .beAnnotatedWith(RegisterRestClient::class.java)
            .because("Accessors should be a rest client.")
            .check(mainClasses)
    }

    @Test
    fun businessLayerDoesNotUseApiLayer() {
        noClasses().that().haveSimpleNameEndingWith(SERVICE)
            .should().accessClassesThat().haveSimpleNameEndingWith(REST_CONTROLLER)
            .because("The business layer should not use the api layer.")
            .check(mainClasses)
    }

    @Test
    fun apiLayerDoesNotUseIntegrationLayer() {
        noClasses().that().haveSimpleNameEndingWith(REST_CONTROLLER)
            .should().accessClassesThat().haveSimpleNameEndingWith(ACCESSOR)
            .because("The api layer should not use the integration layer.")
            .check(mainClasses)
    }
}
