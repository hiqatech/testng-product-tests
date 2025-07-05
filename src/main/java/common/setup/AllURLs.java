package common.setup;

public class AllURLs {

    static String BankingQA = "https://www.globalsqa.com/angularJs-protractor/BankingProject";
    static String BankingUAT = "https://www.globalsqa.com/angularJs-protractor/BankingProject";

    public static String getProductURL()
    {
        if (System.getProperty("product").contains("Banking")){
            if (System.getProperty("environment").contains("QA"))
                return BankingQA;
            else if (System.getProperty("environment").contains("UAT"))
                    return BankingUAT;
            else return "Product URL has not been defined on the environment";
        }
        else return "Product URL has not been defined";
    }

}
