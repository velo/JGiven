package com.tngtech.jgiven;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.tngtech.jgiven.base.ScenarioTestBase;
import com.tngtech.jgiven.report.model.StepModel;

public class FeatureVariablesInMethodNames extends ScenarioTestBase<GivenTestStep, WhenTestStep, ThenTestStep> {

    @Test
    public void variables_are_read_from_method_names() {
        scenario.startScenario( "variables are read from method names" );

        given().$d_and_$d( 5, 6 );
        then().sms_and_emails_exist();

        StepModel stepModel = scenario.getModel().getLastScenarioModel().scenarioCases.get( 0 ).steps.get( 0 );
        assertThat( stepModel.getCompleteSentence() ).isEqualTo( "Given 5 and 6" );
    }
}