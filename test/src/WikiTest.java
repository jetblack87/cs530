import org.wikipedia.Wiki;

public class WikiTest {

	public static void main(String[] args) {
		Wiki wiki;
		File f = new File("wiki.dat");
		if (f.exists()) // we already have a copy on disk
		{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
			wiki = (Wiki) in.readObject();
		} else {
			try {
				wiki = new Wiki("en.wikipedia.org"); // create a new wiki
														// connection to
														// en.wikipedia.org
				wiki.setThrottle(5000); // set the edit throttle to 0.2 Hz
				wiki.login("ExampleBot", password); // log in as user
													// ExampleBot, with the
													// specified password
			} catch (FailedLoginException ex) {
				// deal with failed login attempt
			}
		}
		try {
			for (String page : pages) // pages generated from (say)
										// getCategoryMembers()
			{
				try {
					// do something with page
				}
				// this exception isn't fatal - probably won't affect the task
				// as a whole
				catch (CredentialException ex) {
					// deal with protected page
				}
			}
		}
		// these exceptions are fatal - we need to abandon the task
		catch (CredentialNotFoundException ex) {
			// deal with trying to do something we can't
		} catch (CredentialExpiredException ex) {
			// deal with the expiry of the cookies
		} catch (AccountLockedException ex) {
			// deal with being blocked
		} catch (IOException ex) {
			// deal with network error
		}

	}
}
