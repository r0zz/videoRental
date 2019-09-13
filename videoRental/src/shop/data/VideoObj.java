package shop.data;

/**
 * Implementation of Video interface.
 * @see Data
 */
final class VideoObj implements Video {
  private final String _title;
  private final int    _year;
  private final String _director;

  /**
   * Initialize all object attributes.
   */
  VideoObj(String title, int year, String director) {
    _title = title;
    _director = director;
    _year = year;
  }

  public String director() {
    // TODO  
	  return _director;
  }

  public String title() {
    // TODO  
	  return _title;
  }

  public int year() {
    // TODO  
	  return _year;
  }

  public boolean equals(Object thatObject) {
    // TODO  
	  if (thatObject instanceof VideoObj) {

          // safe type casting

          VideoObj other = (VideoObj) thatObject;

          // checking if all attributes are same in both objects

          return (this._director.equals(other.director()) 
        		  && this._title.equals(other.title()) && this._year == other.year());

	  }
	  return false;
  }

  public int hashCode() {
    // TODO  
	  int result = 17;

      int prime = 37;

      result = prime * result + _title.hashCode();

      result = prime * result + _year;

      result = prime * result + _director.hashCode();

      return result;
  }

  public int compareTo(Object thatObject) {
    // TODO  
	  VideoObj other = (VideoObj) thatObject;

      if (this._title.compareTo(other._title) != 0) {

    	  return this._title.compareTo(other._title);

      } else {

    	  if (this._year != other._year) {

          return this._year - other._year;

          		} else {

                return this._director.compareTo(other._director);

                }
      	}
  }

  public String toString() {
    // TODO  
	  return _title + " (" + _year + ") : " + _director;
  }
}
