import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @(#) RakingList.java
 */

public class RakingList implements Serializable
{
	private List<String[]> rankings;
	
	public RakingList(){
		rankings = new ArrayList<String[]>();
	}
	
	public void addPlayerScore( String PlayerName, int Score )
	{
		String[] ranking = new String[2];
		ranking[0] = PlayerName;
		ranking[1] = Integer.toString(Score);
		rankings.add(ranking);
	}
	
	public void printRankings(){
		rankings.sort(new Comparator<String[]>() {

			@Override
			public int compare(String[] o1, String[] o2) {
				return Integer.compare(Integer.parseInt(o2[1]), Integer.parseInt(o1[1]));
			}
			
		});
		System.out.println("\n-----RANKINGS-----\n");
		System.out.println("NAME - SCORE\n");
		for (String[] rank: rankings){
			System.out.println(rank[0] + " - " + rank[1]);
		}
		
		System.out.println("\n-------END-------\n");
	}
	
	
}
