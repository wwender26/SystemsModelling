import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * @(#) GameController.java
 */
public class GameController {
	private static final int POPULATION_COUNT = 18;

	private Resturaunt resturaunt;

	private List<Client> population;

	private int currentday = 1;

	private RakingList rankingList;

	private Scanner scan = new Scanner(System.in);

	public GameController() {

		try {
			FileInputStream f_in = new FileInputStream("rankings.dat");
			ObjectInputStream obj_in = new ObjectInputStream(f_in);
			rankingList = (RakingList) obj_in.readObject();
			obj_in.close();
		} catch (ClassNotFoundException | IOException e) {
			rankingList = new RakingList();
		}

		resturaunt = new Resturaunt();
		population = new ArrayList<Client>();
		for (int i = 0; i < POPULATION_COUNT; i++) {
			population.add(new Client());
		}
		startGame();
	}

	public void startGame() {

		System.out.println("Welcome to the Resturaunt game!\n");

		whileLoop: while (true) {
			System.out
					.print("Would you like to start a new game (1), view rankings (2) or exit (3): ");
			try {
				int choice = scan.nextInt();
				if (choice >= 1 && choice <= 3) {
					switch (choice) {
					case 1:
						break whileLoop;
					case 2:
						rankingList.printRankings();
						break;
					case 3:
						return;

					}

				}

			} catch (InputMismatchException e) {
				scan = new Scanner(System.in);
			}

		}

		System.out.println("You are now a owner of a resturaunt, good luck!\n");

		System.out.println("Lets set some initial attributes");

		askPlayerDishesAttributes();

		askPlayerBeverageAttributes();

		startGameLoop();

	}

	public void printRankings() {
		rankingList.printRankings();
	}

	public int getNextMove() {
		System.out.println("Please enter your next move.");
		System.out.println("Enter number 1 to start day");
		System.out.println("Enter number 2 to change beverages attributes");
		System.out.println("Enter number 3 to change dishes attributes");
		System.out.println("Enter number 4 to train employees");
		while (true) {

			try {
				System.out.print("Please enter a number between 1-4: ");

				int move = scan.nextInt();
				if (move >= 1 && move <= 4) {

					return move;
				}
			} catch (InputMismatchException e) {
				scan = new Scanner(System.in);
			}

		}

	}

	public List<Client> getVisitors() {
		Collections.shuffle(population);
		int howManyClients;
		List<Client> newList = new ArrayList<Client>();
		switch (resturaunt.getReputation()) {
		case LOW:
			howManyClients = 4;
			break;
		case MEDIUM:
			howManyClients = 10;
			break;
		case HIGH:
			howManyClients = 18;
			break;
		default:
			howManyClients = 0;
		}
		for (int i = 0; i < howManyClients; i++) {
			newList.add(population.get(i));
		}
		return newList;
	}

	private void printstats() {
		System.out.println("Stats:");
		System.out.println("Budget: " + resturaunt.getBudget().getBudget());
		System.out.println("Reputation: " + resturaunt.getReputation());
		System.out.println("ReputationScore: "
				+ resturaunt.getReputationScore());

	}

	public void startGameLoop() {

		while (currentday <= 30) {
			try {
				System.out.println("\n------------\n");

				System.out.println("Its the early morning of day " + currentday + "!");

				dayloop: while (true) {

					printstats();

					switch (getNextMove()) {
					case 1:
						resturaunt.startDay(currentday, getVisitors());
						break dayloop;
					case 2:
						askPlayerBeverageAttributes();
						break;
					case 3:
						askPlayerDishesAttributes();
						break;
					case 4:
						trainEmployee();
						break;
					}
					;
				}

				currentday = currentday + 1;
			} catch (OutOfBudgetException e) {
				System.out
						.println("\nOh No! You ran out of budget and the bank has taken away your resturaunt. Better luck next time!");
				endGame(true);
				return;
			}

		}
		endGame(false);
	}

	public void endGame(boolean outOfBudget) {
		if (!outOfBudget){
			
			saveRankings();
			printstats();
			try {
				FileOutputStream f_out = new FileOutputStream("rankings.dat");

				ObjectOutputStream obj_out = new ObjectOutputStream(f_out);

				obj_out.writeObject(rankingList);

				obj_out.close();
			} catch (IOException e) {
				System.out.println("Error saving rakings!");
			}
		}
	}	
		public void printStats(){
		
		System.out.print("Would you like to print stats for each client? (y for yes)");
		String input = scan.next();
		if (input.equalsIgnoreCase("y")){
			System.out.println("Printing statistics of each client\n");
			for (int i = 0; i<POPULATION_COUNT; i++){
				System.out.println("Client " + i + ".");
				population.get(i).genStatistics();
				System.out.print("\n");
			}
		}	
		
		
		System.out.println("\n---EXITING!---");
		scan.close();
	}
	
	public void saveRankings(){
		System.out.print("\nEnter your name for rankings: ");
		String name = scan.next();
		rankingList.addPlayerScore(name, (int) resturaunt.getBudget().getBudget());
	}

	public void trainEmployee() {
		Budget budget = resturaunt.getBudget();

		while (true) {
			try {
				System.out.println("Your budget is: " + budget.getBudget());
				System.out.println("Who would you like to train?");
				System.out.println("1. Chef - Experience: "
						+ resturaunt.getChef().getExperience());
				System.out.println("2. Barman - Experience: "
						+ resturaunt.getBarman().getExperience());
				System.out.println("3. I Waiter - Experience: "
						+ resturaunt.getWaiters().get(0).getExperience());
				System.out.println("4. II Waiter - Experience: "
						+ resturaunt.getWaiters().get(1).getExperience());
				System.out.println("5. III Waiter - Experience: "
						+ resturaunt.getWaiters().get(2).getExperience());
				System.out.println("6. Cancel");
				System.out.print("Please enter a number between 1-6: ");
				int input = scan.nextInt();
				if (input >= 1 && input <= 6) {
					switch (input) {
					case 1:
						resturaunt.getChef().increaseExperience(budget);
						break;
					case 2:
						resturaunt.getBarman().increaseExperience(budget);
						break;
					case 3:
						resturaunt.getWaiters().get(0)
								.increaseExperience(budget);
						break;
					case 4:
						resturaunt.getWaiters().get(1)
								.increaseExperience(budget);
						break;
					case 5:
						resturaunt.getWaiters().get(2)
								.increaseExperience(budget);
						break;
					case 6:

						return;
					}
				}
			} catch (InsufficientFundsException e) {
				System.out.println("Im sorry, you have insufficent funds!");
			} catch (MaxLevelException e) {
				System.out.println("Already at max level!");
			} catch (InputMismatchException e){
				scan = new Scanner(System.in);
			}

		}
	}

	public void askPlayerDishesAttributes() {
		System.out.println("\nSet the number of good quality dishes");
		int highQdishCount;
		while (true) {
			System.out.print("please enter a number between 0-5: ");
			try {
				highQdishCount = scan.nextInt();
				if (highQdishCount <= 5 && highQdishCount >= 0) {
					break;
				}
			} catch (InputMismatchException e) {
				scan = new Scanner(System.in);
			}
		}
		double priceOfHQdishes;
		System.out.println("Set the price for high quality dish");
		while (true) {
			System.out.print("please enter a number between 0.01-99.9: ");
			try {
				priceOfHQdishes = scan.nextDouble();
				if (priceOfHQdishes <= 99.9 && priceOfHQdishes >= 0.01) {
					break;
				}
			} catch (InputMismatchException e) {
				scan = new Scanner(System.in);
			}
		}
		double priceOfLQdishes;
		System.out.println("Set the price for low quality dish");
		while (true) {
			System.out.print("please enter a number between 0.01-99.9: ");
			try {
				priceOfLQdishes = scan.nextDouble();
				if (priceOfLQdishes <= 99.9 && priceOfLQdishes >= 0.01) {
					break;
				}
			} catch (InputMismatchException e) {
				scan = new Scanner(System.in);
			}
		}

		resturaunt.getMenu().setDisheshAttributes(highQdishCount,
				priceOfHQdishes, priceOfLQdishes);

	}

	public void askPlayerBeverageAttributes() {

		System.out.println("\nSet the number of good quality beverages");
		int highQbeverageCount;
		while (true) {
			System.out.print("please enter a number between 0-5: ");
			try {
				highQbeverageCount = scan.nextInt();
				if (highQbeverageCount <= 5 && highQbeverageCount >= 0) {
					break;
				}
			} catch (InputMismatchException e) {
				scan = new Scanner(System.in);
			}
		}
		double priceOfHQbeverages;
		System.out.println("Set the price for high quality beverage");
		while (true) {
			System.out.print("please enter a number between 0.01-99.9: ");
			try {
				priceOfHQbeverages = scan.nextDouble();
				if (priceOfHQbeverages <= 99.9 && priceOfHQbeverages >= 0.01) {
					break;
				}
			} catch (InputMismatchException e) {
				scan = new Scanner(System.in);
			}
		}
		double priceOfLQbeverages;
		System.out.println("Set the price for low quality beverage");
		while (true) {
			System.out.print("please enter a number between 0.01-99.9: ");
			try {
				priceOfLQbeverages = scan.nextDouble();
				if (priceOfLQbeverages <= 99.9 && priceOfLQbeverages >= 0.01) {
					break;
				}
			} catch (InputMismatchException e) {
				scan = new Scanner(System.in);
			}
		}

		resturaunt.getMenu().setBeverageshAttributes(highQbeverageCount,
				priceOfHQbeverages, priceOfLQbeverages);

	}

}
