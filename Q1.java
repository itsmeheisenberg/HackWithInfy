
/*Q.)Today you decided to go to the gym. You currently have energy equal to E units. There are N exercises in the gym. Each of these exercises drains Ai amount of energy from your body.You feel tired if your energy reaches 0 or below. Calculate the minimum number of exercises you have to perform such that you become tired. Every unique exercise can only be performed at most 2 times as others also have to use the machines.
If performing all the exercises does not make you feel tired, return -1. 
*/
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Q1 {

    static class Exercise {
        int energy;
        int index;

        public Exercise(int energy, int index) {
            this.energy = energy;
            this.index = index;
        }
    }

    public static int minExercise(int exercises, int energy, Exercise[] exerciseEnergy) {
        Arrays.sort(exerciseEnergy, Comparator.comparing((Exercise e) -> e.energy).reversed());
        int totalEnergyDrain = 0;
        for (int i = 0; i < exercises; i++) {
            if (exerciseEnergy[i].energy > energy) {
                continue;
            }
            if (exerciseEnergy[i].energy * 2 <= energy) {
                energy -= exerciseEnergy[i].energy * 2;
                totalEnergyDrain += 2;
            } else {
                energy -= exerciseEnergy[i].energy;
                totalEnergyDrain++;
            }
        }
        if (energy <= 0) {
            return totalEnergyDrain;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter current energy: ");
        int energy = sc.nextInt(); // Initial Energy
        System.out.print("Enter number of exercises: ");
        int exercises = sc.nextInt(); // Number of Exercises
        Exercise[] exerciseEnergy = new Exercise[exercises]; // Energy drained by each exercise
        for (int i = 0; i < exercises; i++) {
            System.out.print("Exercise " + (i + 1) + " with energy: ");
            exerciseEnergy[i] = new Exercise(sc.nextInt(), i + 1); // Storing original index
        }
        int totalExercises = minExercise(exercises, energy, exerciseEnergy);
        if (totalExercises == -1) {
            System.out.print("-1");
        } else {
            System.out.println("Minimum number of exercises needed to become tired:");
            for (int i = 0; i < exercises; i++) {
                int count = 0;
                while (count < 2 && energy >= exerciseEnergy[i].energy) {
                    energy -= exerciseEnergy[i].energy;
                    count++;
                }
                if (count > 0) {
                    System.out.println("Exercise " + exerciseEnergy[i].index + " done " + count + " times");
                }
            }
            System.out.println("Total exercises done: " + totalExercises);
        }
        sc.close();
    }
}