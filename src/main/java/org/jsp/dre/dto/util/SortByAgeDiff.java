package org.jsp.dre.dto.util;

import java.util.Comparator;

import org.jsp.dre.dto.MatchingUser;

public class SortByAgeDiff implements Comparator<MatchingUser>{

	@Override
	public int compare(MatchingUser o1, MatchingUser o2) {
		
		if(o1.getAgedifference() < o2.getAgedifference()) {
			return -1;
		}
		
		else if(o1.getAgedifference() > o2.getAgedifference()){
			
			
			return 1;
		}
		
		else if(o1.getAgedifference() == o2.getAgedifference()) {
			
			if(o1.getMatchingIntrestCount() < o2.getMatchingIntrestCount()) {
				
				return 1;
			}
			else if(o1.getMatchingIntrestCount() > o2.getMatchingIntrestCount()) {
				return -1;
			}
			
			return 0;
			
		}
		
		
				return 0;
	}

	
	
	
	
	
	
	
}
