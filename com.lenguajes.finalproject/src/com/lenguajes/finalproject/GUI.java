package com.lenguajes.finalproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import java.util.concurrent.TimeUnit;

import org.jpl7.*;

public class GUI {

	public static int generateRandom(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	@SuppressWarnings({ "unused", "deprecation" })
	public void DemoPokemon(String chosenPokemon, String direction) throws IOException, InterruptedException {
		int randomPokemon, randomHp, myhp, damage, enemydmg, count = 0, randomMove, squirtleMove, mydmg, charmanderMove,
				bulbasaurMove;
		Query getRandomPokemon, whoWon, getRandomMove, getSquirtleMove, getCharmanderMove, getBulbasaurMove;
		Term nameRandom, typeRandom, moveName, lowerdamage, higherdamage, squirtleName, lowerdamageS, higherdamageS,
				moveType, eff1, eff2, charmanderName, bulbasaurName;
		Map<String, Term> solution4RandomP, solution4RandomM, solution4SquirtleM, solutionvsEnemy, solutionvsMe,
				solution4CharmanderM, solution4BulbasaurM;
		Query chosenPokedex = new Query("pokedex(" + chosenPokemon + ",Number,Type)");
		if (chosenPokedex.hasSolution()) {
			Map<String, Term> solution = chosenPokedex.getSolution();
			Term numberChosen = solution.get("Number");
			Term TypeChosen = solution.get("Type");
			// System.out.println("Choose where to move, Left ,straight, Right");

			switch (direction) {
			case "Left":
			case "left":
				randomPokemon = generateRandom(1, 50); // should be 50
				getRandomPokemon = new Query("pokedex(Name," + randomPokemon + ",Type)");
				solution4RandomP = getRandomPokemon.getSolution();
				nameRandom = solution4RandomP.get("Name");
				typeRandom = solution4RandomP.get("Type");

				System.out.println("Your opponent sent out " + nameRandom);
				randomHp = generateRandom(15, 20) * 10;
				myhp = generateRandom(15, 20) * 10;
				while (randomHp >= 0 && myhp >= 0) {
					// if(tackle) {

					randomMove = generateRandom(1, 8);
					getRandomMove = new Query(typeRandom + "Power(MoveName," + randomMove + ",Type,LDmg,HDmg)");
					solution4RandomM = getRandomMove.getSolution();
					moveName = solution4RandomM.get("MoveName");
					moveType = solution4RandomM.get("Type");
					lowerdamage = solution4RandomM.get("LDmg");
					higherdamage = solution4RandomM.get("HDmg");

					squirtleMove = generateRandom(1, 8);
					getSquirtleMove = new Query("waterPower(SquirtleMove," + randomMove + ",Type,LDmgS,HDmgS)");
					solution4SquirtleM = getSquirtleMove.getSolution();
					squirtleName = solution4SquirtleM.get("SquirtleMove");
					lowerdamageS = solution4SquirtleM.get("LDmgS");
					higherdamageS = solution4SquirtleM.get("HDmgS");

					int number = lowerdamage.intValue();
					int number2 = higherdamage.intValue();

					int number3 = lowerdamageS.intValue();
					int number4 = higherdamageS.intValue();

					Query vsEnemy = new Query("xAgainstY(water," + typeRandom + ",MyEff)");
					solutionvsEnemy = vsEnemy.getSolution();
					eff1 = solutionvsEnemy.get("MyEff");

					Query vsMe = new Query("xAgainstY(" + moveType + ",water,EneEff)");
					solutionvsMe = vsMe.getSolution();
					eff2 = solutionvsMe.get("EneEff");

					System.out.println("You used " + squirtleName);
					if (eff1.intValue() == 1) {
						mydmg = generateRandom(number3, number4) * 12;
						System.out.println("It's super effective");
					}
					if (eff1.intValue() == 2) {
						mydmg = generateRandom(number3, number4) * 8;
						System.out.println("It's not very effective");
					}
					if (eff1.intValue() == 3) {
						mydmg = generateRandom(number3, number4) * 0;
						System.out.println("Does not affect " + nameRandom);
					} else
						mydmg = generateRandom(number3, number4) * 10;

					System.out.println("You did " + mydmg + " damage");
					randomHp = randomHp - mydmg;
					System.out.println("your opponent has " + randomHp + " Hp left");

					System.out.println("Your oponent used " + moveName);
					if (eff2.intValue() == 1) {
						enemydmg = generateRandom(number3, number4) * 12;
						System.out.println("It's super effective");
					}
					if (eff2.intValue() == 2) {
						enemydmg = generateRandom(number3, number4) * 8;
						System.out.println("It's not very effective");
					}
					if (eff2.intValue() == 3) {
						enemydmg = generateRandom(number3, number4) * 0;
						System.out.println("Does not affect Squirtle");
					} else
						enemydmg = generateRandom(number3, number4) * 10;

					System.out.println("Enemy did " + enemydmg + " damage");
					myhp = myhp - enemydmg;
					System.out.println("Your pokemon has " + myhp + " Hp left");
					TimeUnit.SECONDS.sleep(1);
				}

				if (randomHp <= 0 && myhp <= 0) {
					System.out.println("Both pokemon fainted");
				} else if (randomHp <= 0) {
					System.out.println("You Won");
				} else if (myhp <= 0) {
					System.out.println("You Lost");
				}

				break;

			case "Right":
			case "right":
				randomPokemon = generateRandom(51, 100); // should be 50
				getRandomPokemon = new Query("pokedex(Name," + randomPokemon + ",Type)");
				solution4RandomP = getRandomPokemon.getSolution();
				nameRandom = solution4RandomP.get("Name");
				typeRandom = solution4RandomP.get("Type");

				System.out.println("Your opponent sent out " + nameRandom);
				randomHp = generateRandom(15, 20) * 10;
				myhp = generateRandom(15, 20) * 10;
				while (randomHp >= 0 && myhp >= 0) {
					// if(tackle) {

					randomMove = generateRandom(1, 8);
					getRandomMove = new Query(typeRandom + "Power(MoveName," + randomMove + ",Type,LDmg,HDmg)");
					solution4RandomM = getRandomMove.getSolution();
					moveName = solution4RandomM.get("MoveName");
					moveType = solution4RandomM.get("Type");
					lowerdamage = solution4RandomM.get("LDmg");
					higherdamage = solution4RandomM.get("HDmg");

					bulbasaurMove = generateRandom(1, 8);
					getBulbasaurMove = new Query("grassPower(BulbasaurMove," + randomMove + ",Type,LDmgS,HDmgS)");
					solution4BulbasaurM = getBulbasaurMove.getSolution();
					bulbasaurName = solution4BulbasaurM.get("BulbasaurMove");
					lowerdamageS = solution4BulbasaurM.get("LDmgS");
					higherdamageS = solution4BulbasaurM.get("HDmgS");

					int number = lowerdamage.intValue();
					int number2 = higherdamage.intValue();

					int number3 = lowerdamageS.intValue();
					int number4 = higherdamageS.intValue();

					Query vsEnemy = new Query("xAgainstY(grass," + typeRandom + ",MyEff)");
					solutionvsEnemy = vsEnemy.getSolution();
					eff1 = solutionvsEnemy.get("MyEff");

					Query vsMe = new Query("xAgainstY(" + moveType + ",grass,EneEff)");
					solutionvsMe = vsMe.getSolution();
					eff2 = solutionvsMe.get("EneEff");

					System.out.println("You used " + bulbasaurName);
					if (eff1.intValue() == 1) {
						mydmg = generateRandom(number3, number4) * 12;
						System.out.println("It's super effective");
					}
					if (eff1.intValue() == 2) {
						mydmg = generateRandom(number3, number4) * 8;
						System.out.println("It's not very effective");
					}
					if (eff1.intValue() == 3) {
						mydmg = generateRandom(number3, number4) * 0;
						System.out.println("Does not affect " + nameRandom);
					} else
						mydmg = generateRandom(number3, number4) * 10;

					System.out.println("You did " + mydmg + " damage");
					randomHp = randomHp - mydmg;
					System.out.println("your opponent has " + randomHp + " Hp left");

					System.out.println("Your oponent used " + moveName);
					if (eff2.intValue() == 1) {
						enemydmg = generateRandom(number3, number4) * 12;
						System.out.println("It's super effective");
					}
					if (eff2.intValue() == 2) {
						enemydmg = generateRandom(number3, number4) * 8;
						System.out.println("It's not very effective");
					}
					if (eff2.intValue() == 3) {
						enemydmg = generateRandom(number3, number4) * 0;
						System.out.println("Does not affect Bulbasaur");
					} else
						enemydmg = generateRandom(number3, number4) * 10;

					System.out.println("Enemy did " + enemydmg + " damage");
					myhp = myhp - enemydmg;
					System.out.println("Your pokemon has " + myhp + " Hp left");
					TimeUnit.SECONDS.sleep(1);
				}

				if (randomHp <= 0 && myhp <= 0) {
					System.out.println("Both pokemon fainted");
				} else if (randomHp <= 0) {
					System.out.println("You Won");
				} else if (myhp <= 0) {
					System.out.println("You Lost");
				}

				break;

			case "Straight":
			case "straight":
				randomPokemon = generateRandom(101, 151); // should be 50
				getRandomPokemon = new Query("pokedex(Name," + randomPokemon + ",Type)");
				solution4RandomP = getRandomPokemon.getSolution();
				nameRandom = solution4RandomP.get("Name");
				typeRandom = solution4RandomP.get("Type");

				System.out.println("Your opponent sent out " + nameRandom);
				randomHp = generateRandom(15, 20) * 10;
				myhp = generateRandom(15, 20) * 10;
				while (randomHp >= 0 && myhp >= 0) {
					// if(tackle) {

					randomMove = generateRandom(1, 8);
					getRandomMove = new Query(typeRandom + "Power(MoveName," + randomMove + ",Type,LDmg,HDmg)");
					solution4RandomM = getRandomMove.getSolution();
					moveName = solution4RandomM.get("MoveName");
					moveType = solution4RandomM.get("Type");
					lowerdamage = solution4RandomM.get("LDmg");
					higherdamage = solution4RandomM.get("HDmg");

					charmanderMove = generateRandom(1, 8);
					getCharmanderMove = new Query("firePower(CharmanderMove," + randomMove + ",Type,LDmgS,HDmgS)");
					solution4CharmanderM = getCharmanderMove.getSolution();
					charmanderName = solution4CharmanderM.get("CharmanderMove");
					lowerdamageS = solution4CharmanderM.get("LDmgS");
					higherdamageS = solution4CharmanderM.get("HDmgS");

					int number = lowerdamage.intValue();
					int number2 = higherdamage.intValue();

					int number3 = lowerdamageS.intValue();
					int number4 = higherdamageS.intValue();

					Query vsEnemy = new Query("xAgainstY(fire," + typeRandom + ",MyEff)");
					solutionvsEnemy = vsEnemy.getSolution();
					eff1 = solutionvsEnemy.get("MyEff");

					Query vsMe = new Query("xAgainstY(" + moveType + ",fire,EneEff)");
					solutionvsMe = vsMe.getSolution();
					eff2 = solutionvsMe.get("EneEff");

					System.out.println("You used " + charmanderName);
					if (eff1.intValue() == 1) {
						mydmg = generateRandom(number3, number4) * 12;
						System.out.println("It's super effective");
					}
					if (eff1.intValue() == 2) {
						mydmg = generateRandom(number3, number4) * 8;
						System.out.println("It's not very effective");
					}
					if (eff1.intValue() == 3) {
						mydmg = generateRandom(number3, number4) * 0;
						System.out.println("Does not affect " + nameRandom);
					} else
						mydmg = generateRandom(number3, number4) * 10;

					System.out.println("You did " + mydmg + " damage");
					randomHp = randomHp - mydmg;
					System.out.println("your opponent has " + randomHp + " Hp left");

					System.out.println("Your oponent used " + moveName);
					if (eff2.intValue() == 1) {
						enemydmg = generateRandom(number3, number4) * 12;
						System.out.println("It's super effective");
					}
					if (eff2.intValue() == 2) {
						enemydmg = generateRandom(number3, number4) * 8;
						System.out.println("It's not very effective");
					}
					if (eff2.intValue() == 3) {
						enemydmg = generateRandom(number3, number4) * 0;
						System.out.println("Does not affect Charmander");
					} else
						enemydmg = generateRandom(number3, number4) * 10;

					System.out.println("Enemy did " + enemydmg + " damage");
					myhp = myhp - enemydmg;
					System.out.println("Your pokemon has " + myhp + " Hp left");
					TimeUnit.SECONDS.sleep(1);
				}

				if (randomHp <= 0 && myhp <= 0) {
					System.out.println("Both pokemon fainted");
				} else if (randomHp <= 0) {
					System.out.println("You Won");
				} else if (myhp <= 0) {
					System.out.println("You Lost");
				}

				break;

			}

		} else {
			System.out.println("There is no such pokemon " + chosenPokemon);
		}

	}

	@SuppressWarnings({ "unused", "deprecation" })
	public static void WhoCanMyPokemonBeat(String chosenPokemon) throws IOException {
		Query canBeat, pokename;
		Map<String, Term> solution;
		Term nameOther, type;
		ArrayList<Term> pokemons = new ArrayList<Term>();

		for (int i = 1; i < 152; i++) {
			pokename = new Query("pokedex(Name," + i + ",Type)");
			solution = pokename.getSolution();
			nameOther = solution.get("Name");
			type = solution.get("Type");
			canBeat = new Query("canXWin(" + chosenPokemon + "," + nameOther + ")");
			if (canBeat.hasSolution()) {
				pokemons.add(nameOther);
			}
		}
		System.out.println("In theory you can beat this pokemons");
		System.out.println(pokemons);

	}

	@SuppressWarnings("deprecation")
	public static void PokemonStats(String chosenPokemon) throws IOException {
		Query pokePowers, pokename;
		Map<String, Term> solution;
		Term type, powerName;
		int rand;
		ArrayList<Term> powers = new ArrayList<Term>();
		pokename = new Query("pokedex(" + chosenPokemon + ",Number,Type)");
		if (pokename.hasSolution()) {
			solution = pokename.getSolution();
			type = solution.get("Type");
			for (int i = 1; i < 5; i++) {
				rand = generateRandom(1, 8);
				pokePowers = new Query(type + "Power(Name," + rand + ",Type,Lower,Max)");
				solution = pokePowers.getSolution();
				powerName = solution.get("Name");
				powers.add(powerName);
			}
			System.out.println("Your pokemon " + chosenPokemon);
			System.out.println("Hp : " + generateRandom(40, 110));
			System.out.println("Attack : " + generateRandom(40, 120));
			System.out.println("Defense : " + generateRandom(40, 120));
			System.out.println("Special Attack : " + generateRandom(30, 150));
			System.out.println("Special defense : " + generateRandom(50, 100));
			System.out.println("Speed : " + generateRandom(30, 160));
			System.out.println("Got the powers " + powers);
		} else {
			System.out.println("There is no such pokemon " + chosenPokemon);
		}
	}

	public static void PokemonBattle(String who) throws IOException {
		String firstPokemon, secondPokemon, thirdPokemon, forthPokemon;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Choose your first pokemon to send out");
		firstPokemon = reader.readLine().replace("\n", "").replace("\r", "");
		System.out.println("Choose your second pokemon to send out");
		secondPokemon = reader.readLine().replace("\n", "").replace("\r", "");
		System.out.println("Choose your third pokemon to send out");
		thirdPokemon = reader.readLine().replace("\n", "").replace("\r", "");
		System.out.println("Choose your forth pokemon to send out");
		forthPokemon = reader.readLine().replace("\n", "").replace("\r", "");
//		System.out.println("Choose who you will fight: red, ash, blue, green, misty or brock");
//		who = reader.readLine().replace("\n", "").replace("\r", "");

		Query firstPokemonPokedex = new Query("pokedex(" + firstPokemon + ",Number,Type)");
		Query secondPokemonPokedex = new Query("pokedex(" + secondPokemon + ",Number,Type)");
		Query thirdPokemonPokedex = new Query("pokedex(" + thirdPokemon + ",Number,Type)");
		Query forthPokemonPokedex = new Query("pokedex(" + forthPokemon + ",Number,Type)");
		Query trainer = new Query("collection(" + who + ",List)");

		System.out.println(firstPokemonPokedex.hasSolution() ? "" : "Theres no such pokemon as " + firstPokemon);
		System.out.println(secondPokemonPokedex.hasSolution() ? "" : "Theres no such pokemon as " + secondPokemon);
		System.out.println(thirdPokemonPokedex.hasSolution() ? "" : "Theres no such pokemon as " + thirdPokemon);
		System.out.println(forthPokemonPokedex.hasSolution() ? "" : "Theres no such pokemon as " + forthPokemon);
		System.out.println(trainer.hasSolution() ? "" : "Theres no such trainer as " + who);

		if (firstPokemonPokedex.hasSolution() && secondPokemonPokedex.hasSolution() && thirdPokemonPokedex.hasSolution()
				&& forthPokemonPokedex.hasSolution() && trainer.hasSolution()) {

			Query fight = new Query("pokemonBattle([" + firstPokemon + "," + secondPokemon + "," + thirdPokemon + "," + forthPokemon
					+ "]," + who + ",Z)");
			Map<String, Term> solutionFight = fight.getSolution();
			Term won = solutionFight.get("Z");
			if(won.intValue() == 1) {
				System.out.println("Both trainers ran out of pokemon, both blacked out " + who);
			}
			if(won.intValue() == 2) {
				System.out.println("You won the fight against " + who);
			}
			if(won.intValue() == 3) {
				System.out.println("You lost the fight against " + who);
			}
		}
	}

	public final static void clearConsole() {
		try {
			final String os = System.getProperty("os.name");
			if (os.contains("Windows")) {
				Runtime.getRuntime().exec("cls");
			} else {
				Runtime.getRuntime().exec("clear");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String t1 = "consult('src/com/lenguajes/finalproject/Pokemon.pl')";
		Query q1 = new Query(t1);
		System.out.println(q1.hasMoreSolutions() ? "Compiled correctly" : "Prepare to cry");

		int count = 0;

		if (count == 0) {
			count++;
			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run() {
					new MainMenu().setVisible(true);
				}
			});
		}

//		new GUI();
//		int whatToPlay;
//		
//		Scanner scanner = new Scanner( System.in );
//
//		
//		while (true) {
//	        System.out.println("What function do you wish to see");
//	        System.out.println("1. Demo Pokemon");
//	        System.out.println("2. Who can your pokemon beat");
//	        System.out.println("3. Wild encounter");
//	        System.out.println("4. Fight with a legendary team");
//	       
//
//	        whatToPlay = scanner.nextInt();
//
//	        switch(whatToPlay) {
//	        case 1:
//	            DemoPokemon();
//	        break;
//
//	        case 2:
//	            WhoCanMyPokemonBeat();
//	        break;
//
//	        case 3:
//	            PokemonStats();
//	        break;
//
//	        case 4:
//	            PokemonBattle();
//	        break;
//
//	        }
//	        TimeUnit.SECONDS.sleep(1); 
//	        //clearConsole();  
//	        }
		// DemoPokemon();
		// WhoCanMyPokemonBeat();
		// PokemonStats();
		// PokemonBattle();
	}

}
