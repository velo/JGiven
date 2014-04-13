package com.tngtech.jgiven.report.html;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import com.tngtech.jgiven.junit.ScenarioTest;
import com.tngtech.jgiven.report.json.GivenJsonReports;
import com.tngtech.jgiven.tags.Feature;
import com.tngtech.jgiven.tags.FeatureEnum;
import com.tngtech.jgiven.test.Story;

@RunWith( DataProviderRunner.class )
public class FrameBaseHtmlReportGeneratorTest extends ScenarioTest<GivenJsonReports<?>, WhenHtmlReportGenerator, ThenHtmlReportGenerator> {

    @DataProvider
    public static Object[][] testNumbers() {
        return new Object[][] { { 0 }, { 1 }, { 3 } };
    }

    @Test
    @UseDataProvider( "testNumbers" )
    @Story( "JGIVEN-1" )
    @Feature( FeatureEnum.HtmlReport )
    public void the_frame_based_reporter_generates_one_file_for_each_test_class( int n ) throws IOException {
        given()
            .$_report_models( n )
            .and().the_reports_exist_as_JSON_files();

        when()
            .the_frame_based_HTML_reporter_is_executed();

        then()
            .an_index_file_exists()
            .and().an_HTML_file_exists_for_each_test_class();
    }

    @Test
    @Feature( { FeatureEnum.Tags, FeatureEnum.HtmlReport } )
    public void the_frame_based_reporter_generates_one_file_for_each_tag() throws IOException {
        given().a_report_model()
            .and().the_first_scenario_has_tag( "TestTag" )
            .and().the_report_exist_as_JSON_file();

        when().the_frame_based_HTML_reporter_is_executed();

        then().a_file_with_name_$_exists( "TestTag.html" );
    }

}
