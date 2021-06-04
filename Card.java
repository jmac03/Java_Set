public class Card
{
    public int value1;  // color
    public int value2;  // shape
    public int value3;  // filling
    public int value4;  // number
    public int[] valueList = {value1, value2, value3, value4};

    public String displayCard(Card card){
            return "[" + value1 + "," + value2 + "," + value3 + "," + value4 + "]";
        }
}
