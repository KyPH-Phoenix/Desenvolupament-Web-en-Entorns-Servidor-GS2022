package Exercici1;

public class DiscountRate {
    private static double serviceDiscountPremium = 0.2;
    private static double serviceDiscountGold = 0.15;
    private static double serviceDiscountSilver = 0.1;
    private static double productDiscountPremium = 0.1;
    private static double productDiscountGold = 0.1;
    private static double productDiscountSilver = 0.1;

    public static double getServiceDiscountRate(String type) {
        if (type.toLowerCase().equals("silver")) return serviceDiscountSilver;
        if (type.toLowerCase().equals("gold")) return serviceDiscountGold;
        if (type.toLowerCase().equals("premium")) return serviceDiscountPremium;
        return 0;
    }

    public static double getProductDiscountRate(String type) {
        if (type.toLowerCase().equals("silver")) return productDiscountSilver;
        if (type.toLowerCase().equals("gold")) return productDiscountGold;
        if (type.toLowerCase().equals("premium")) return productDiscountPremium;
        return 0;
    }

}
