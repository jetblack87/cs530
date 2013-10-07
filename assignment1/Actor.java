/*
 * Mark Albrecht
 * mwa29@drexel.edu
 * CS530:DUI, Assignment 1
 */

import java.awt.Color;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Random;

public class Actor {

	enum Expression {
		HAPPY, NEUTRAL, SAD
	};

	public static final Color PURPLE = new Color(255, 0, 255);
	public static final HashMap<String, Color> ALLOWED_COLORS = new HashMap<String, Color>();
	static {
		ALLOWED_COLORS.put("Red", Color.RED);
		ALLOWED_COLORS.put("Orange", Color.ORANGE);
		ALLOWED_COLORS.put("Yellow", Color.YELLOW);
		ALLOWED_COLORS.put("Green", Color.GREEN);
		ALLOWED_COLORS.put("Blue", Color.BLUE);
		ALLOWED_COLORS.put("Purple", PURPLE);
		ALLOWED_COLORS.put("Black", Color.BLACK);
		ALLOWED_COLORS.put("White", Color.WHITE);
	}

	public static final int MAX_HEIGHT = 150;
	public static final int MAX_WIDTH = 150;

	public static final int MIN_HEIGHT = 25;
	public static final int MIN_WIDTH = 25;

	private String name;
	private Expression expression;
	private Color shirtColor;
	private Color pantsColor;
	private int height;
	private int width;

	private static Random rnd = new Random();

	
	public Actor() {
		super();
		this.name = getRandomActorName();
		this.expression = getRandomExpression();
		this.shirtColor = getRandomColor();
		this.pantsColor = getRandomColor();
		this.height = MAX_HEIGHT;
		this.width = MAX_WIDTH;
	}

	public Actor(Actor actor) {
		this.name = actor.name;
		this.expression = actor.getExpression();
		this.shirtColor = actor.getShirtColor();
		this.pantsColor = actor.getPantsColor();
		this.height = actor.height;
		this.width = actor.width;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	public Color getShirtColor() {
		return shirtColor;
	}

	public void setShirtColor(Color shirtColor)
			throws InvalidParameterException {
		boolean found = false;
		for (String strColor : ALLOWED_COLORS.keySet()) {
			if (shirtColor.equals(ALLOWED_COLORS.get(strColor))) {
				found = true;
			}
		}
		if (found) {
			this.shirtColor = shirtColor;
		} else {
			throw new InvalidParameterException("Invalid color was given: "
					+ shirtColor);
		}
	}

	public Color getPantsColor() {
		return pantsColor;
	}

	public void setPantsColor(Color pantsColor) {
		this.pantsColor = pantsColor;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String toString() {
		return name;
	}

	public static String getColorName(Color color) {
		for (String strColor : ALLOWED_COLORS.keySet()) {
			if (color.equals(ALLOWED_COLORS.get(strColor))) {
				return strColor;
			}
		}
		return null;
	}

	private static String[] actors = { "Robert De Niro", "Al Pacino", "Tom Hanks",
			"Johnny Depp", "Jack Nicholson", "Marlon Brando", "Meryl Streep",
			"Leonardo DiCaprio", "Morgan Freeman", "Denzel Washington",
			"Anthony Hopkins", "Dustin Hoffman", "Clint Eastwood", "Brad Pitt",
			"Will Smith", "Russell Crowe", "Daniel Day Lewis", "Sean Penn",
			"James Stewart", "Paul Newman", "Humphrey Bogart", "Heath Ledger",
			"Edward Norton", "Tom Cruise", "Kate Winslet", "Kevin Spacey",
			"Mel Gibson", "Harrison Ford", "Matt Damon", "Jim Carrey",
			"Cary Grant", "Audrey Hepburn", "Bette Davis", "Katharine Hepburn",
			"Bruce Willis", "Christian Bale", "Julia Roberts",
			"Robert Downey Jr.", "Sean Connery", "Samuel L Jackson",
			"Laurence Olivier", "Robin Williams", "George Clooney",
			"Nicolas Cage", "John Wayne", "Cate Blanchett", "Robert Duvall",
			"Sandra Bullock", "Gregory Peck", "Spencer Tracy", "Gene Hackman",
			"Clark Gable", "Angelina Jolie", "Henry Fonda", "John Travolta",
			"Gary Cooper", "Jodie Foster", "Sylvester Stallone",
			"Ingrid Bergman", "Gary Oldman", "Charlie Chaplin",
			"Nicole Kidman", "Robert Redford", "Phillip Seymour Hoffman",
			"Judi Dench", "James Dean", "Adam Sandler", "Charlton Heston",
			"Liam Neeson", "Christopher Walken", "Ian McKellen",
			"Michael Caine", "Hugh Jackman", "Jack Lemmon", "Charlize Theron",
			"Steve McQueen", "Vivien Leigh", "Julie Andrews", "James Cagney",
			"Viggo Mortensen", "Orlando Bloom", "Peter O'Toole",
			"Reese Witherspoon", "Elizabeth Taylor", "Shia LaBeouf",
			"Burt Lancaster", "Toshiro Mifune", "Michael Douglas",
			"Kevin Costner", "Alec Guinness", "Joe Pesci",
			"Helena Bonham Carter", "Emma Thompson", "Mark Wahlberg",
			"Orson Welles", "Sidney Poitier", "Keira Knightley",
			"Diane Keaton", "Halle Berry", "Grace Kelly" };
	
	private static String getRandomActorName() {
		return actors[rnd.nextInt(actors.length)];
	}
	
	private static Expression getRandomExpression() {
		Expression[] expressions = Expression.values();
		return expressions[rnd.nextInt(expressions.length)];
	}
	
	private static Color getRandomColor() {
		String[] colors = new String[ALLOWED_COLORS.size()];
		ALLOWED_COLORS.keySet().toArray(colors);
		String color = colors[rnd.nextInt(colors.length)];
		return ALLOWED_COLORS.get(color);
	}


}
