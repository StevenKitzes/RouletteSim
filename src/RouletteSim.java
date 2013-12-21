/**
 * Created with IntelliJ IDEA.
 * User: Steven Kitzes
 * Date: 12/21/13
 * Time: 11:27 AM
 * To change this template use File | Settings | File Templates.
 */

import java.lang.NumberFormatException;

public class RouletteSim {
    public static void main(String args[]) {
        Integer spins = -1;

        if( args.length > 0 ) {
            try {
                spins = Integer.parseInt(args[0]);
                if( spins < 1 ) throw new NumberFormatException("Illegal negative number of spins!");
            } catch ( NumberFormatException nfe ) {
                System.out.println("FATAL ERROR:\nInvalid argument received at program initialization.\n");
                System.out.println(nfe.getMessage());
                return;
            }

            RouletteSim app = new RouletteSim(spins.intValue());
        }   //  if args received
        else {
            System.out.println("No spin limit received.  Quitting.  (Please enter total spins as arg at command line.)");
        }
        return;
    }

    public RouletteSim(int spins) {

        int cash = 1000, spin, numHits = 0, colorHits = 0, attempts = 0;

        System.out.println("\nWelcome to my silly roulette simulator.\n");
        System.out.println("My strategy is to place equal bets of 5 on my favorite number,");
        System.out.println("and on the color opposite to that of my favorite number for each");
        System.out.println("spin. Currently simulating on " + spins + " spins with " + cash + " starting cash.\n");

        //  for each of the total number of designated spins
        for( int i = 0; i < spins; i++) {
            //  place bets
            cash -= 10;

            if( cash < 0 ) {
                System.out.println("Ran out of money!  Epic fail.");
                break;
            }

            //  resolve spin
            attempts++;
            spin = (int)(Math.random() * 38) - 1;

            //  if direct hit on number bet
            if( spin == 10 ) {
                //  payout
                cash += 175;
                numHits++;
            }
            //  if hit on off-color bet
            else if( spin > 18 ) {
                cash += 10;
                colorHits++;
            }
        }   //  for all spins

        System.out.println("After " + attempts + " attempts, cash = " + cash + " after " + numHits + " number hits and " + colorHits + " color hits.");
    }
}
