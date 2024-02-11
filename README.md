# HELP4DEVS - JAVA
A simple repository to serve various functions and methods made in Java

> This repository is a little project to help the Java developers on your daily needs. In this project, you can find 
> many resources that are commonly used in most of the situations during the specific needed or requirement to solve 
> any issue or problem from any case.

# Summary

The content that you will find out in this project are:

- <a href="#base">Base</a>
- <a href="#currency">Currency</a>
- <a href="#database">Database</a>
- <a href="#date">Date</a>
- <a href="#filehandler">FileHandler</a>
- <a href="#filereader">FileReader</a>
- <a href="#path">Path</a>
- <a href="#stringhandler">StringHandler</a>
- <a href="#tools">Tools</a>
- <a href="#validator">Validator</a>

> IMPORTANT NOTE:<br>
> All methods and functionalities are placed int the project scope in the path src/main/java/com/huntercodexs/demo/services
> and all tests are placed in the test scope from src/test/java/codexstester/test/unitary/Help4DevsUnitaryTests.java.
> You can follow this documentation or read and execute directly the @Test for each function afford here.

# Base

[Help4DevsBaseService.java](src/main/java/com/huntercodexs/demo/services/Help4DevsBaseService.java)

> public static void params(JSONObject... jsonObjects)

This method offer the functionality to receive all arguments was passed as the parameters, for example:

<pre>
    public static void params(JSONObject... jsonObjects) {
        System.out.println(Arrays.toString(jsonObjects));
    }
</pre>

<pre>
    @Test
    public void paramsTests() {
        JSONObject j1 = new JSONObject();
        j1.put("name", "Test 1");

        JSONObject j2 = new JSONObject();
        j2.put("name", "Test 2");

        params(j1, j2);
    }
</pre>

In this case we have the following result

<pre>
[{"name":"Test 1"}, {"name":"Test 2"}]
</pre>

Look at the above result and see that there two objects of type JSON, and they were passed to the method in the separated 
way, however the method have a single expected argument (JSONObject... jsonObjects) that automatically convert these 
parameters to a list parameters with a type define in the signature.

> public static String numberFormatter(int input, String format)

The method numberFormatter is shortcut to format a number in the specific way. Maybe you need format one number from 
left to right filling using a specific number. Below is the common use to this functionality:

<pre>
    public static String numberFormatter(int input, String format) {
        return String.format(format, input);
    }
</pre>

<pre>
    @Test
    public void numberFormatterTest() {
        String result = numberFormatter(1, "%09d");
        System.out.println(result);
    }
</pre>

As a result we have the following output

<pre>
000000001
</pre>

> public static String stringFormatter(String input, String format)

The following method afford the function that format one string in a specific way. Below are the method mentioned in 
this topic, look at it how simple is the implementation

<pre>
    public static String stringFormatter(String input, String format) {
        return String.format(format, input);
    }
</pre>

<pre>
    @Test
    public void stringFormatterTest() {
        String result1 = stringFormatter("XXX", "%10s");
        System.out.println("["+result1+"]");

        String result2 = stringFormatter("XXX", "%-10s");
        System.out.println("["+result2+"]");
    }
</pre>

In this case, we are formatting one string XXX with 10 spaces from the left to right and also from the right to left. It 
can be seen when the second parameter is set using a character "-" in front of the number quantity formatter. The result 
for both cases above are showed below

Using %10s

<pre>
[       XXX]
</pre>

Using %-10s

<pre>
[XXX       ]
</pre>

> public static String fillerFormatter(String input, String filler, String align, int size)

This functionality offer a better way to format one string with a specific char, that can be a number or a string, and 
also can be made from left to right and right to left, below we have one simple example to explain in the better way 
what we're talking about, let's look at it. 

<pre>
    public static String fillerFormatter(String input, String fill, String align, int size) {

        if (!align.equals("left") && !align.equals("right")) {
            System.out.println("Error: use left or right to param [align]");
            return null;
        }

        if (size < 0) {
            System.out.println("Error: use size > 0");
            return null;
        }

        String formatted = input;

        int lenValue = input.length();
        int lenFill = size - lenValue;
        String repeat = fill.repeat(lenFill);

        if (align.equals("left")) {
            formatted = input + repeat;
        } else {
            formatted = repeat + input;
        }

        return formatted;
    }
</pre>

<pre>
    @Test
    public void fillerFormatterTest() {
        String result1 = fillerFormatter("XXX", "F", "left", 20);
        System.out.println("["+result1+"]");

        String result2 = fillerFormatter("XXX", "F", "right", 20);
        System.out.println("["+result2+"]");

        String result3 = fillerFormatter("ZZZ", "8", "left", 20);
        System.out.println("["+result3+"]");

        String result4 = fillerFormatter("ZZZ", "8", "right", 20);
        System.out.println("["+result4+"]");

        String result5 = fillerFormatter("YYY", "A", "left", -20);
        System.out.println("["+result5+"]");

        String result6 = fillerFormatter("YYY", "A", "right", -20);
        System.out.println("["+result6+"]");
    }
</pre>

The result will be something like below

<pre>
[XXXFFFFFFFFFFFFFFFFF]
[FFFFFFFFFFFFFFFFFXXX]
[ZZZ88888888888888888]
[88888888888888888ZZZ]
Error: use size > 0
[null]
Error: use size > 0
[null]
</pre>

> public static String rgFormatter(String value, String rgUf)

This method is only used in the Brazil, because it serves to specific situation in that country, where we need to manager 
or formatter the document number or document type from anyone in the system. So, maybe we need to add any information 
or remove the information from document (RG), in these case we can use this method in the way showed below

<pre>
    public static String rgFormatter(String value, String rgUf) {
        if (value == null || value.equals("")) return "";
        if (rgUf == null) rgUf = "";
        if (!rgUf.contains("SSP") && !rgUf.equals("")) return "";

        //SSP CP = SSPSC, SSP/SP = SSPSP
        rgUf = rgUf.replaceAll("[^A-Z]+", "");

        if (rgUf.equals("SSPSP") || rgUf.equals("SP")) {
            rgUf = "";
        } else {
            //SSPCRJ = RJ, SSPSC = SC
            rgUf = rgUf.replaceAll("SSP", "");
        }

        return "RG"+value.replaceAll("[^0-9]+", "")+rgUf;
    }
</pre>

<pre>
    @Test
    public void rgFormatterTest() {
        System.out.println(" > ["+ rgFormatter("9090909090", "CNH")+"]");
        System.out.println(" > ["+ rgFormatter("7878787878", "SSP SC")+"]");
        System.out.println(" > ["+ rgFormatter("6767676767", "SSPSP")+"]");
        System.out.println(" > ["+ rgFormatter("1212121212", "SSCMG")+"]");
        System.out.println(" > ["+ rgFormatter("2020202020", "SSP/RJ")+"]");
    }
</pre>

The result will be something like the below

<pre>
 > []
 > [RG7878787878SC]
 > [RG6767676767]
 > []
 > [RG2020202020RJ]
</pre>

Look that this method manager the information and makes a few validations to format the documentation identification RG 
in the form correct form for each place in the Brazil, and not accept empty rgUf. 

# Currency

[Help4DevsCurrencyService.java](src/main/java/com/huntercodexs/demo/services/Help4DevsCurrencyService.java)

> public static String brCurrency(float value)

This method only works with Reais currency that are used in Brazil. To use it, just pass one parameter value in float 
format, below we can see an example using this method.

<pre>
    public static String brCurrency(float value) {
        if (value <= 0) return "";
        Locale localBrazil = new Locale("pt", "BR");
        NumberFormat brCurrency = NumberFormat.getCurrencyInstance(localBrazil);
        return brCurrency.format(value)
                .replaceAll("[^0-9R$., ]+", "")
                .replaceAll("R[$]", "R\\$ ");
    }
</pre>

<pre>
    @Test
    public void brCurrencyFloatTest() {
        System.out.println(brCurrency(Float.parseFloat("999111111111.00")));
    }
</pre>

Result

<pre>
R$ 999.111.131.136,00
</pre>

> public static String brCurrency(double value)

In this case we have the same idea or purpose that was used in the previous method above. However, in this case we need 
to pay attention in the type of parameter, that should be double, not float. Below it's possible see the correct use 
fo this method and the result

<pre>
    public static String brCurrency(double value) {
        if (value <= 0) value = 0.00;
        Locale localBrazil = new Locale("pt", "BR");
        NumberFormat brCurrency = NumberFormat.getCurrencyInstance(localBrazil);
        return brCurrency.format(value)
                .replaceAll("[^0-9R$., ]+", "")
                .replaceAll("R[$]", "R\\$ ");
    }
</pre>

<pre>
    @Test
    public void brCurrencyDoubleTest() {
        System.out.println(brCurrency(999111111111.00));
    }
</pre>

Result

<pre>
R$ 999.111.111.111,00
</pre>

> public static double currencySum(double current, double add)

<pre>
    public static double currencySum(double current, double add) {
        System.out.println(brCurrency(current) +"+"+ brCurrency(add));
        double sum = current + add;
        System.out.println(brCurrency((float) sum));
        return sum;
    }
</pre>

<pre>
    @Test
    public void currencySumTest() {

        double result = currencySum(0.00, 0.01);
        result += currencySum(0.01, 0.10);
        result += currencySum(0.10, 0.11);
        result += currencySum(0.11, 1.11);
        result += currencySum(1.00, 1.01);
        result += currencySum(1.00, 1.10);
        result += currencySum(11.00, 111.10);
        result += currencySum(1111.00, 11.10);
        result += currencySum(111111.00, 111.10);
        result += currencySum(111.00, 11.01);
        result += currencySum(111111111.00, 11.01);
        result += currencySum(999111111111.00, 11.01);

        System.out.println("Total");
        System.out.println(brCurrency(result));

        /*Proof*/
        double proff = currencySum(0.00, 1.00);
        proff += currencySum(0.00, 1.00);
        proff += currencySum(0.00, 1.00);
        proff += currencySum(0.00, 1.00);
        proff += currencySum(0.00, 1.00);
        proff += currencySum(0.00, 1.00);

        System.out.println("Total");
        System.out.println(brCurrency(proff));
        codexsTesterAssertText("R$ 6,00", brCurrency(proff));

    }
</pre>

Result

<pre>
R$ 0,00+R$ 0,01
R$ 0,01
R$ 0,01+R$ 0,10
R$ 0,11
R$ 0,10+R$ 0,11
R$ 0,21
R$ 0,11+R$ 1,11
R$ 1,22
R$ 1,00+R$ 1,01
R$ 2,01
R$ 1,00+R$ 1,10
R$ 2,10
R$ 11,00+R$ 111,10
R$ 122,10
R$ 1.111,00+R$ 11,10
R$ 1.122,10
R$ 111.111,00+R$ 111,10
R$ 111.222,10
R$ 111,00+R$ 11,01
R$ 122,01
R$ 111.111.111,00+R$ 11,01
R$ 111.111.120,00
R$ 999.111.111.111,00+R$ 11,01
R$ 999.111.131.136,00
Total
R$ 999.222.334.837,99
R$ 0,00+R$ 1,00
R$ 1,00
R$ 0,00+R$ 1,00
R$ 1,00
R$ 0,00+R$ 1,00
R$ 1,00
R$ 0,00+R$ 1,00
R$ 1,00
R$ 0,00+R$ 1,00
R$ 1,00
R$ 0,00+R$ 1,00
R$ 1,00
Total
R$ 6,00
</pre>

> public static double currencySumFromString(String current, String add)

In the same way, that was presented in the method above, we can make a sum of the number that are in string format. Just 
pay attention when the parameter are passed in the request to guarantee that those are from string type, for example:

<pre>
    public static double currencySumFromString(String current, String add) {
        System.out.println(brCurrency(Double.parseDouble(current)) +"+"+ brCurrency(Double.parseDouble(add)));
        double sum = Double.parseDouble(current) + Double.parseDouble(add);
        System.out.println(brCurrency((float) sum));
        return sum;
    }
</pre>

<pre>
    public void currencySumFromStringTest() {

        /*Proof*/
        double proff = currencySumFromString("0.00", "1.00");
        proff += currencySumFromString("0.00", "1.00");
        proff += currencySumFromString("0.00", "1.00");
        proff += currencySumFromString("0.00", "1.00");
        proff += currencySumFromString("0.00", "1.00");
        proff += currencySumFromString("0.00", "1.00");

        System.out.println("Total");
        System.out.println(brCurrency(proff));
        codexsTesterAssertText("R$ 6,00", brCurrency(proff));

    }
</pre>

Result, see that even the params was in the string type, this method can be handling that situation

<pre>
R$ 0,00+R$ 1,00
R$ 1,00
R$ 0,00+R$ 1,00
R$ 1,00
R$ 0,00+R$ 1,00
R$ 1,00
R$ 0,00+R$ 1,00
R$ 1,00
R$ 0,00+R$ 1,00
R$ 1,00
R$ 0,00+R$ 1,00
R$ 1,00
Total
R$ 6,00
</pre>

# Database

[Help4DevsDatabaseService.java](src/main/java/com/huntercodexs/demo/services/Help4DevsDatabaseService.java)

# Date

[Help4DevsDateService.java](src/main/java/com/huntercodexs/demo/services/Help4DevsDateService.java)

> public static String reverseDate(String inputDate, String separator)

Whether you need make one date revert in the position of each part from any date, this is the correct and useful method.
Check the algorithm from this method inside the Help4DevsDateService.java file.

To use this method, you can follow the example below

<pre>
    @Test
    public void reverseDateTest() {
        System.out.println(reverseDate("14/07/2023 14:53:25", "-"));
        System.out.println(reverseDate("2023-08-16 16:10:28", "/"));
        System.out.println(reverseDate("14/07/2023 14:53:25", "/"));
        System.out.println(reverseDate("2023-08-16 16:10:28", "-"));

        System.out.println(reverseDate("14/07/2023TZ14:53:25", "-"));
        System.out.println(reverseDate("2023-08-16TZ16:10:28", "/"));
        System.out.println(reverseDate("14/07/2023TZ14:53:25", "/"));
        System.out.println(reverseDate("2023-08-16TZ16:10:28", "-"));
        System.out.println(reverseDate("2023-08-15T18:02:26.737Z", "-"));
        System.out.println(reverseDate("2023-08-15T18:02:26.737Z", "/"));

        System.out.println(reverseDate("14/07/2023", "-"));
        System.out.println(reverseDate("2023-08-16", "/"));
        System.out.println(reverseDate("14/07/2023", "/"));
        System.out.println(reverseDate("2023-08-16", "-"));
    }
</pre>

The expected result is something like that

<pre>
2023-07-14 14:53:25
16/08/2023 16:10:28
2023/07/14 14:53:25
16-08-2023 16:10:28
2023-07-14 14:53:25
16/08/2023 16:10:28
2023/07/14 14:53:25
16-08-2023 16:10:28
15-08-2023 18:02:26.737
15/08/2023 18:02:26.737
2023-07-14
16/08/2023
2023/07/14
16-08-2023
</pre>

> public static boolean expiredDate(String date, int time, String metricType)

To check if any date is outdated, due or any other case, use the method expiredDate, for example:

<pre>
    @Test
    public void expiredDateTest() {
        boolean time = expiredDate("14/07/2023 14:53:25", 1, "nano");
        System.out.println("RESULT IS [NANO]: " + time);

        time = expiredDate("14/07/2023 14:53:25", 1, "second");
        System.out.println("RESULT IS [SECOND]: " + time);

        time = expiredDate("14/07/2023 14:53:25", 1, "minute");
        System.out.println("RESULT IS [MINUTE]: " + time);

        time = expiredDate("14/07/2023 14:53:25", 1, "hour");
        System.out.println("RESULT IS [HOUR]: " + time);

        time = expiredDate("14/07/2023 14:53:25", 1, "day");
        System.out.println("RESULT IS [DAY]: " + time);

        time = expiredDate("14/07/2023 14:53:25", 1, "week");
        System.out.println("RESULT IS [WEEK]: " + time);

        time = expiredDate("14/07/2023 14:53:25", 1, "month");
        System.out.println("RESULT IS [MONTH]: " + time);

        time = expiredDate("14/07/2023 14:53:25", 1, "year");
        System.out.println("RESULT IS [YEAR]: " + time);
    }
</pre>

In this case we have the following result

<pre>
MATCH 1: 14/07/2023 14:53:25
2024-02-09 15:01:35.026  INFO jereelton-acer-nitro --- [           main] c.h.d.s.Help4DevsDateService             : Expired time: (now)2024-02-09T15:01:35 - (limit)2023-07-14T14:53:25.000000001
RESULT IS [NANO]: true
MATCH 1: 14/07/2023 14:53:25
2024-02-09 15:01:35.026  INFO jereelton-acer-nitro --- [           main] c.h.d.s.Help4DevsDateService             : Expired time: (now)2024-02-09T15:01:35 - (limit)2023-07-14T14:53:26
RESULT IS [SECOND]: true
MATCH 1: 14/07/2023 14:53:25
2024-02-09 15:01:35.027  INFO jereelton-acer-nitro --- [           main] c.h.d.s.Help4DevsDateService             : Expired time: (now)2024-02-09T15:01:35 - (limit)2023-07-14T14:54:25
RESULT IS [MINUTE]: true
MATCH 1: 14/07/2023 14:53:25
2024-02-09 15:01:35.027  INFO jereelton-acer-nitro --- [           main] c.h.d.s.Help4DevsDateService             : Expired time: (now)2024-02-09T15:01:35 - (limit)2023-07-14T15:53:25
RESULT IS [HOUR]: true
MATCH 1: 14/07/2023 14:53:25
2024-02-09 15:01:35.028  INFO jereelton-acer-nitro --- [           main] c.h.d.s.Help4DevsDateService             : Expired time: (now)2024-02-09T15:01:35 - (limit)2023-07-15T14:53:25
RESULT IS [DAY]: true
MATCH 1: 14/07/2023 14:53:25
2024-02-09 15:01:35.028  INFO jereelton-acer-nitro --- [           main] c.h.d.s.Help4DevsDateService             : Expired time: (now)2024-02-09T15:01:35 - (limit)2023-07-21T14:53:25
RESULT IS [WEEK]: true
MATCH 1: 14/07/2023 14:53:25
2024-02-09 15:01:35.029  INFO jereelton-acer-nitro --- [           main] c.h.d.s.Help4DevsDateService             : Expired time: (now)2024-02-09T15:01:35 - (limit)2023-08-14T14:53:25
RESULT IS [MONTH]: true
MATCH 1: 14/07/2023 14:53:25
RESULT IS [YEAR]: false
</pre>

> public static List<Long> quantifyDate(String initialDate, String finalDate)

With this method you can quantify one time in years, months, days, hours, minutes, seconds, milliseconds to get full 
information about one specific time. The result will be something like below

<pre>
    public static List<Long> quantifyDate(String initialDate, String finalDate) {
        ...
    }
</pre>

<pre>
    @Test
    public void quantifyDateTest() {
        quantifyDate("14/07/2023 15:53:25", "14/07/2023 15:53:26");
    }
</pre>

<pre>
MATCH 1: 14/07/2023 15:53:25
MATCH 1: 14/07/2023 15:53:26
[0, 0, 0, 0, 0, 1, 0]
RESULT: 0 years, 0 months, 0 days, 0 hours, 0 minutes, 1 seconds, 0 milliseconds
</pre>

> public static long quantifyMillisDate()

<pre>
    public static long quantifyMillisDate() {

        /*Sample functionality*/

        long startDate = Calendar.getInstance().getTimeInMillis();

        try {
            Thread.sleep(3200);

            long endDate = Calendar.getInstance().getTimeInMillis();
            long duration = endDate - startDate;

            System.out.println("StartDate: " + startDate);
            System.out.println("EndDate: " + endDate);
            System.out.println("Duration: " + duration + " milliseconds");

            return duration;

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
</pre>

The result should be something like below

<pre>
StartDate: 1707675336041
EndDate: 1707675339242
Duration: 3201 milliseconds
</pre>

> public static long quantifyMillisParamsDate(String start, String end)

<pre>
    public static long quantifyMillisParamsDate(String start, String end) {
        ...
    }
</pre>

<pre>
    @Test
    public void quantifyMillisParamsDateTest() {
        quantifyMillisParamsDate("2023/08/20 15:30:10.100", "2023/08/20 15:31:10.500");
        quantifyMillisParamsDate("2023/08/20 15:30:10", "2023/08/20 15:31:10");
        quantifyMillisParamsDate("2023/08/20 15:30", "2023/08/20 15:31");
        quantifyMillisParamsDate("2023/08/20 15", "2023/08/20 16");
        quantifyMillisParamsDate("2023/08/20", "2023/08/21");

        quantifyMillisParamsDate("2023-08-20 15:30:10.100", "2023-08-20 15:30:10.500");
        quantifyMillisParamsDate("2023-08-20 15:30:10", "2023-08-20 15:31:10");
        quantifyMillisParamsDate("2023-08-20 15:30", "2023-08-20 15:31");
        quantifyMillisParamsDate("2023-08-20 15", "2023-08-20 16");
        quantifyMillisParamsDate("2023-08-20", "2023-08-21");
    }
</pre>

The result look like as below

<pre>
DURATION: 60400
DURATION: 60000
DURATION: 60000
DURATION: 3600000
DURATION: 86400000
DURATION: 400
DURATION: 60000
DURATION: 60000
DURATION: 3600000
DURATION: 86400000
</pre>

> public static String localDateFromGmtDate(String gmtDate, String operation, int time)

# FileHandler

[Help4DevsFileHandlerService.java](src/main/java/com/huntercodexs/demo/services/Help4DevsFileHandlerService.java)

> public static Properties loadProps(String classpath)

> public static InputStream bytesExtractorShipmentFile(String targetPath, String targetFile)

> public static InputStream fileToByteArray(String targetPath, String targetFile)

> public static ByteArrayDataSource fileToDataSource(String targetPath, String targetFile) throws IOException

> public static byte[] byteConvert(InputStream fileArray) throws IOException

> public static String fileToString(String targetPath, String targetFile)

> public static ArrayList<String> fileToArray(String targetPath, String targetFile)

# FileReader

[Help4DevsFileReaderService.java](src/main/java/com/huntercodexs/demo/services/Help4DevsFileReaderService.java)

> public static FileReader open(String filepath)

> public static BufferedReader buffer(FileReader activateFile)

> public static String reader(BufferedReader readActivateFile)

> public static void close(FileReader activateFile)

> public static String getFileContent(String filepath, String regex, int timeout)

# Path

[Help4DevsPathService.java](src/main/java/com/huntercodexs/demo/services/Help4DevsPathService.java)

> public static String sanitizePath(String path)

> public static String sanitizeAscii(String input)

# StringHandler

[Help4DevsStringHandlerService.java](src/main/java/com/huntercodexs/demo/services/Help4DevsStringHandlerService.java)

> public static String queryStringBuilder(Object input)

> public static String getDataFromQueryString(String queryString, String field)

> public static JSONObject queryStringToJson(String input)

> public static JSONObject stringToJson(String str)

# Tools

[Help4DevsToolsService.java](src/main/java/com/huntercodexs/demo/services/Help4DevsToolsService.java)

> public static String md5(String data)

> public static String guide(String tcn)

# Validator

[Help4DevsValidatorService.java](src/main/java/com/huntercodexs/demo/services/Help4DevsValidatorService.java)

> public static boolean cpfValidator(String cpf)

> public static boolean mailValidator(String email)

> public static boolean phoneValidator(String phoneNumber)
