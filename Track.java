public class Track implements Comparable<Track>{
	
	private String name;
	private double quality;
	private int index;
	
	public Track(String name, double quality, int index){
		this.name = name;
		this.quality = quality;
		this.index = index;	
	}

	//Track creation must include arguments
	private Track(){
		
	}
	
	public String getName(){
		return name;
	}

	@Override
	public int compareTo(Track s) {
        if(this.quality < s.quality) 
                return 1;
        else if (quality == s.quality)
                return (index < s.index) ? -1 : 1;
        else
                return -1;
	}
	
}
