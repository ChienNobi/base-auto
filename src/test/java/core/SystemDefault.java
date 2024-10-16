package core;

import helpers.CommonHelper;

public class SystemDefault {
    public static final String RESOURCE_PATH = CommonHelper.getCurrentDir() + "src/test/resources/";
    public static final String TEST_DATA_FILE = RESOURCE_PATH + "data/TestData.xlsx";
    public static final String RESULT_FILE = RESOURCE_PATH + "data/TestResult.xlsx";
    public static final String SQL_INJECTION_RESULT_FILE = RESOURCE_PATH + "data/SQLInjection.xlsx";
    public static final String XSS_RESULT_FILE = RESOURCE_PATH + "data/XSSSearch.xlsx";
    public static final int WAIT_IMPLICIT = 5000;
}
