import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Deck extends Card {
    // The list that holds all the inactive cards
    public static List<Card> deck = new ArrayList<Card>();
    // The list that holds all the active cards
    public static List<Card> activeDeck = new ArrayList<Card>();
    // The random number generator
    public static Random random = new Random();
    // The object that handles input
    public static Scanner input = new Scanner(System.in);

    /**
     * This method is run first, calls the starting methods and prepares the game. 
     */
    public static void main(String args[]){
        System.out.println("Hello World!");
        createDeck();
        dealDeck();
        startGame();
    }

    /**
     * Fills deck with cards.
     */
    private static void createDeck(){
    
        for (Integer a = 1; a  < 4; a++) {
            for (Integer b = 1; b< 4; b++) {
                for (Integer c = 1; c < 4; c++) {
                    for (Integer d = 1; d < 4; d++) {
                        // Create card
                        Card card = new Card();
                        card.value1 = a;
                        card.value2 = b;
                        card.value3 = c;
                        card.value4 = d;
                        // Add the new card to the active deck
                        deck.add(card);
                        
                    }
                }
            }
        }
    }

    /**
     * Fills active deck with cards.
     */
    private static void dealDeck(){
        for(int counter=activeDeck.size(); counter<12; counter++){
            int selected_card_index = random.nextInt(deck.size());
            Card selected_card = deck.get(selected_card_index);
            activeDeck.add(selected_card);
            deck.remove(selected_card_index);
        }
        System.out.println("active deck is: ");
        int counter = 1;
        String separator = ": ";
        for (Card card : activeDeck) {
            if(counter >= 10){separator = ":";}
            System.out.println(counter + separator + card.displayCard(card)); 
            counter += 1;
        }
    }

    /**
     * Manages gameplay, starting and continuing the game
     */
    public static void startGame(){
        // start play loop
        System.out.println("Game has started");
        // cuts off game when deck is emptied
        while(deck.size() > 0){ 
            // refill the active deck or allow player to select cards 
            if(activeDeck.size() < 12){
                dealDeck();
            }
            else{
                selectCards();
            }
        }
        System.out.println("You finished the game!");  // currently end of game
        input.close();
    }

    /**
     * Allows player to select three cards. If those cards form a set,
     * remove those cards from the deck.
     */
    private static void selectCards(){
        // get first card
        System.out.print("Enter the first card you want to pick (1-12): ");
        int firstIndex = input.nextInt()-1;
        while(firstIndex > activeDeck.size()){
            System.out.println("That was not a valid choice.");
            System.out.print("Enter the first card you want to pick (1-12): ");
            firstIndex = input.nextInt()-1;
        }
        System.out.println("You selected card #" + (firstIndex + 1) + 
        " which is: " + activeDeck.get(firstIndex).displayCard(activeDeck.get(firstIndex)));
        // get second card
        System.out.print("Enter the second card you want to pick (1-12): ");
        int secondIndex = input.nextInt()-1;
        while(secondIndex == firstIndex || secondIndex > activeDeck.size()){
            System.out.println("That was not a valid choice.");
            System.out.print("Enter the second card you want to pick (1-12): ");
            secondIndex = input.nextInt()-1;
        }
        System.out.println("You selected card #" + (secondIndex + 1) + 
        " which is: " + activeDeck.get(secondIndex).displayCard(activeDeck.get(secondIndex)));
        // get the third card
        System.out.print("Enter the third card you want to pick (1-12): ");
        int thirdIndex = input.nextInt()-1;
        while(thirdIndex == secondIndex || thirdIndex == firstIndex || thirdIndex >activeDeck.size()){
            System.out.println("That was not a valid choice.");
            System.out.print("Enter the third card you want to pick (1-12): ");
            thirdIndex = input.nextInt()-1;
        }
        System.out.println("You selected card #" + (thirdIndex + 1) + 
        " which is: " + activeDeck.get(thirdIndex).displayCard(activeDeck.get(thirdIndex)));
        if(isSet(activeDeck.get(firstIndex), activeDeck.get(secondIndex), activeDeck.get(thirdIndex)) == true){  // set this == true or you will only lose cards when you don't get a set
            // remove the matching cards
            if(firstIndex > secondIndex && firstIndex > thirdIndex){
                activeDeck.remove(firstIndex);
                activeDeck.remove(Math.max(secondIndex, thirdIndex));
                activeDeck.remove(Math.min(secondIndex, thirdIndex));
            }
            if(secondIndex > firstIndex && secondIndex > thirdIndex){
                activeDeck.remove(secondIndex);
                activeDeck.remove(Math.max(firstIndex, thirdIndex));
                activeDeck.remove(Math.min(firstIndex, thirdIndex));

            }
            if(thirdIndex > firstIndex && thirdIndex > secondIndex){
                activeDeck.remove(thirdIndex);
                activeDeck.remove(Math.max(firstIndex, secondIndex));
                activeDeck.remove(Math.min(firstIndex, secondIndex));
            }
            System.out.println("You got a set!");
        }
        else{
            System.out.println("That is not a set. Please try again");
        }
    }



    /**
     * Checks three cards to see if they form a set.
     * @param card1: The first card.
     * @param card2: The second card.
     * @param card3: The third card.
     * @return True if the card form a set, false if not.
     */
    private static boolean isSet(Card card1, Card card2, Card card3){
        int firstTrait = card1.value1 + card2.value1 + card3.value1;
        int secondTrait = card1.value2 + card2.value2 + card3.value2;
        int thirdTrait = card1.value3 + card2.value3 + card3.value3;
        int fourthTrait = card1.value4 + card2.value4 + card3.value4;
        if(firstTrait == 3 || firstTrait == 6 || firstTrait == 9){
         if(secondTrait == 3 || secondTrait == 6 || secondTrait == 9){
          if(thirdTrait == 3 || thirdTrait == 6 || thirdTrait == 9){
           if(fourthTrait == 3 || fourthTrait == 6 || fourthTrait == 9){
               return true;
           }
          }
         }
        }
        return false;
     }

}