import java.util.*;
import java.lang.*;

class Timetable{
	private String Place1;
	private String Place2;
	private LinkedList<Flight> flightList;	

	Timetable(String Place1, String Place2, LinkedList<Flight> flightList){
		this.flightList = new LinkedList<Flight>(flightList);
		this.Place1 = Place1;
		this.Place2 = Place2;
	}	

	public String getInicial(){
		return Place1;
	}

	public String getFinal(){
		return Place2;
	}

	public LinkedList<Flight> getFlights(){
		return flightList;
	}
}

class Flight{
	private String Place1;
	private String Place2;
	private HashSet<String> dayArray;
	private String flightNum;
	private String Dep_Time;
	private String Arr_Time;

	Flight(String Place1, String Place2, HashSet<String> dayArray, String flightNum, String Dep_Time, String Arr_Time){
		this.dayArray = new HashSet<String>(dayArray);
		this.Place1 = Place1;
		this.Place2 = Place2;
		this.flightNum = flightNum;
		this.Dep_Time = Dep_Time;
		this.Arr_Time = Arr_Time;
	}

	public HashSet<String> getDays(){
		return dayArray;
	}

	public String toString(){
		return ("Flight Number: " + flightNum + " | Departure Time: " + Dep_Time + " | Arrival Time: " + Arr_Time);	
	}
}



public class Avionetas{
	static HashSet<String> alldays = new HashSet<String>(Arrays.asList("mo", "tu", "we", "th", "fr", "sa", "su"));
	
	static int menu(Scanner input){
		System.out.println("*** MENU ***");
		System.out.println("1. Search for direct flight");
		System.out.println("2. Check for the available flights on certain day");
		System.out.println("3. One flight a day");
		System.out.println("4. Exit");

		System.out.print("Insert the number of the option you want to choose: ");
		int option = input.nextInt();

		while(option != 1 && option != 2 && option != 3 && option != 4){
			System.out.print("Please, choose an option from the menu! Type again: ");
			option = input.nextInt();
		}

		return option;

	}

	static String printDay(String day){
		if(day.equals("mo")){
			return "Monday";
		}
		else if(day.equals("tu")){
			return "Tuesday";
		}
		else if(day.equals("we")){
			return "Wednesday";
		}
		else if(day.equals("th")){
			return "Thursday";
		}
		else if(day.equals("fr")){
			return "Friday";
		}
		else if(day.equals("sa")){
			return "Saturday";
		}
		else if(day.equals("su")){
			return "Sunday";
		}
		else{
			return "Error";
		}
	}

	static String nextDay(String day){
		if(day.equals("mo")){
			return "tu";
		}
		else if(day.equals("tu")){
			return "we";
		}
		else if(day.equals("we")){
			return "th";
		}
		else if(day.equals("th")){
			return "fr";
		}
		else if(day.equals("fr")){
			return "sa";
		}
		else if(day.equals("sa")){
			return "su";
		}
		else if(day.equals("su")){
			return "mo";
		}
		else{
			return "Error";
		}
	}

	static void check_direct_flight(String Place1, String Place2, LinkedList<Timetable> timetable_List){
		//System.out.println("yoyoyo");
		int check = getPositionInList(Place1, Place2, timetable_List);
		if(check == -1){
			System.out.println("There are no direct flights from " + Place1 + " to " + Place2 + ". We are sorry!");
			return;			
		}
		

		//System.out.println("CHECK = " + check);

		Timetable important = timetable_List.get(check);
		LinkedList<Flight> list_of_flights = important.getFlights();
		LinkedList<String> days = new LinkedList<String>();
		//System.out.println("yoyoyo");
		//System.out.println("LISTA:" + important.getFlights());
		for(int i = 0; i < list_of_flights.size(); i++){
			//System.out.println("yoyoyo");
			HashSet<String> temp = new HashSet<String>(list_of_flights.get(i).getDays());
			//System.out.println(temp);
			if(temp.equals(alldays)){
				System.out.println();
				System.out.println("All days!");
				System.out.println();
				return;
			}

			else{
				days = new LinkedList<String>(intersect(days, temp.toArray(new String[temp.size()])));
				//System.out.println(days);
			}
		}


		//System.out.println(days);

		if(days.size() == 7){
			System.out.println();
			System.out.println("All days!");
			System.out.println();
			return;
		}


		else{
			System.out.println();

			if(days.contains("mo"))
				System.out.print("Monday ");

			if(days.contains("tu"))
				System.out.print("Tuesday ");
			
			if(days.contains("we"))
				System.out.print("Wednesday ");

			if(days.contains("th"))
				System.out.print("Thursday ");

			if(days.contains("fr"))
				System.out.print("Friday ");

			if(days.contains("sa"))
				System.out.print("Saturday ");

			if(days.contains("su"))
				System.out.print("Sunday");
		}


		System.out.println();
		System.out.println();
		
		return;
	
	}



	static void check_available_flights_on_day(String Place1, String Place2, String day, LinkedList<Timetable> timetable_List){
		if(printDay(day).equals("Error")){
			System.out.println("Invalid day");
			return;
		}

		int check = getPositionInList(Place1, Place2, timetable_List);
		if(check == -1){
			System.out.println("There are no direct flights from " + Place1 + " to " + Place2 + ". We are sorry!");
			return;			
		}


		Timetable important = timetable_List.get(check);
		LinkedList<Flight> list_of_flights = important.getFlights();

		String show = "";
		for(int i = 0; i < list_of_flights.size(); i++){
			HashSet<String> days = new HashSet<String>(list_of_flights.get(i).getDays());
			
			if(days.contains(day)){
				show += list_of_flights.get(i).toString() + "\n";
			}
		}

		if(!show.equals("")){
			System.out.println();
			System.out.println("*Available Flights on " + printDay(day) + "*");
			System.out.println(show);
			return;
		}

		else{
			System.out.println("There are no available flights on " + printDay(day) + ". We are sorry!");
			return;
		}
		
		
	}

	static void check_one_flight_a_day(String start, String Place1, String Place2, String Place3, String day, LinkedList<Timetable> timetable_List){
		if(printDay(day).equals("Error")){
			System.out.println("Invalid day");
			return;
		}

		LinkedList<String> for_Start = new LinkedList<String>(toStart(start, Place1, Place2, Place3, day, timetable_List));
		String finalday = nextDay(nextDay(nextDay(day)));
		LinkedList<String> for_Final = new LinkedList<String>(toFinal(start, Place1, Place2, Place3, finalday, timetable_List));
		LinkedList<String> total = new LinkedList<String>(Arrays.asList(Place1, Place2, Place3));

		Scanner input = new Scanner(System.in);
		for(int i = 0; i < for_Start.size(); i++){
			String p = for_Start.get(i);
			total.remove(p);
			//input.nextLine();
			for(int j = 0; j < 2; j++){
				String x = total.get(j);
				int check = getPositionInList(p, x, timetable_List);
				if(check == -1)
					break;
				Timetable p_x = timetable_List.get(check);
				LinkedList<Flight> list_of_flights = new LinkedList<Flight>(p_x.getFlights());
				int eval = 0;		
				//input.nextLine();
				for(int t = 0; t < list_of_flights.size(); t++){
					HashSet<String> days = new HashSet<String>(list_of_flights.get(t).getDays());
					if(days.contains(nextDay(day))){
						eval = 1;
						break;
					}
				}
				//input.nextLine();
				if(eval == 1){
					total.remove(x);
					String u = total.get(0);
					if(!for_Final.contains(u)){
						total.add(x);
						break;					
					}
					
				
					else{
					//input.nextLine();
						int check2 = getPositionInList(x, u, timetable_List);
						
						if(check2 == -1){
							total.add(x);
							break;
						}
						
						Timetable x_u = timetable_List.get(check2);
						//System.out.println("2");
						LinkedList<Flight> list_of_flights2 = new LinkedList<Flight>(x_u.getFlights());	
						//System.out.println("3");
						for(int t = 0; t < list_of_flights2.size(); t++){
							//System.out.println("4");
							HashSet<String> days = new HashSet<String>(list_of_flights2.get(t).getDays());
	    						if(days.contains(nextDay(nextDay(day)))){
								System.out.println(p + " -> " + x + " -> " + u);
								return;
							}
						}
						
						total.add(x);
					}	
				}
				
			}
			total.add(p);		
		}
		System.out.println("Impossivel!");
		return;
	}

	static LinkedList<String> toStart(String start, String Place1, String Place2, String Place3, String day, LinkedList<Timetable> timetable_List){
		
		LinkedList<String> toReturn = new LinkedList<String>();		

		int check_1 = getPositionInList(start, Place1, timetable_List);
		int check_2 = getPositionInList(start, Place2, timetable_List);
		int check_3 = getPositionInList(start, Place3, timetable_List);
		if(check_1 != -1){
			Timetable start_Place1 = timetable_List.get(check_1);
			LinkedList<Flight> list_of_flights = new LinkedList<Flight>(start_Place1.getFlights());
			checkToStart(Place1, list_of_flights, day, toReturn);
		}
		if(check_2 != -1){
			Timetable start_Place2 = timetable_List.get(check_2);
			LinkedList<Flight> list_of_flights = new LinkedList<Flight>(start_Place2.getFlights());
			checkToStart(Place2, list_of_flights, day, toReturn);
		}
		if(check_3 != -1){
			Timetable start_Place3 = timetable_List.get(check_3);
			LinkedList<Flight> list_of_flights = new LinkedList<Flight>(start_Place3.getFlights());
			checkToStart(Place3, list_of_flights, day,toReturn);
		}
		
		
		
		//System.out.println("START = " + toReturn);
		return toReturn;

	}

	static LinkedList<String> toFinal(String start, String Place1, String Place2, String Place3, String day, LinkedList<Timetable> timetable_List){
		
		LinkedList<String> toReturn = new LinkedList<String>();		

		int check_1 = getPositionInList(Place1, start, timetable_List);
		int check_2 = getPositionInList(Place2, start, timetable_List);
		int check_3 = getPositionInList(Place3, start, timetable_List);

		if(check_1 != -1){
			Timetable Place1_start = timetable_List.get(check_1);
			LinkedList<Flight> list_of_flights = new LinkedList<Flight>(Place1_start.getFlights());
			checkToFinal(Place1, list_of_flights, day, toReturn);
		}

		if(check_2 != -1){
			Timetable Place2_start = timetable_List.get(check_2);
			LinkedList<Flight> list_of_flights = new LinkedList<Flight>(Place2_start.getFlights());
			checkToFinal(Place2, list_of_flights, day, toReturn);
		}

		if(check_3 != -1){
			Timetable Place3_start = timetable_List.get(check_3);
			LinkedList<Flight> list_of_flights = new LinkedList<Flight>(Place3_start.getFlights());
			checkToFinal(Place3, list_of_flights, day, toReturn);
		}
		

		//System.out.println("FINAL = " + toReturn);
		return toReturn;

	}

	static void checkToStart(String place, LinkedList<Flight> list, String day, LinkedList<String> toReturn){
		for(int i = 0; i < list.size(); i++){
			HashSet<String> days = new HashSet<String>(list.get(i).getDays());
			
			if(days.contains(day)){
				toReturn.add(place);
				return;
			}
		}
		
		return;
	}

	static void checkToFinal(String place, LinkedList<Flight> list, String day, LinkedList<String> toReturn){
		//System.out.println(list);
		for(int i = 0; i < list.size(); i++){
			HashSet<String> days = new HashSet<String>(list.get(i).getDays());
			
			if(days.contains(day)){
				toReturn.add(place);
				return;
			}
		}
		
		return;
	}

	static int getPositionInList(String Place1, String Place2, LinkedList<Timetable> timetable_List){
		int check = -1;
		for(int i = 0; i < timetable_List.size(); i++){
			String inicial = timetable_List.get(i).getInicial();
			String arrival = timetable_List.get(i).getFinal();
			//System.out.println("inicial = " + inicial);
			//System.out.println("final = " + arrival);
			if(inicial.equals(Place1) && arrival.equals(Place2)){
				check = i;
				break;
			}
		}
		
		return check;	
	}

	static LinkedList<String> intersect(LinkedList<String> days, String[] temp){
		for(int i = 0; i < temp.length; i++){
			if(!days.contains(temp[i])){
				days.add(temp[i]);
			}
		}
		return days;
	}

	static int calcHours(String hour){
		String split[] = hour.split(":");
		String calc = split[0] + split[1];
		return Integer.parseInt(calc);
	}	

	public static void main(String[] args){
		LinkedList<Timetable> timetable_List = new LinkedList<Timetable>();
		Scanner input = new Scanner(System.in);
		LinkedList<Flight> flights = new LinkedList<Flight>();

		flights.add(new Flight("edinburgh", "london", alldays, "ba4733", "09:40", "10:50"));
		flights.add(new Flight("edinburgh", "london", alldays, "ba4773", "13:40", "14:50"));
		flights.add(new Flight("edinburgh", "london", new HashSet<String>(Arrays.asList("mo", "tu", "we", "th", "fr", "su")), "ba4833", "19:40", "20:50"));
		
		LinkedList<Flight> normal = new LinkedList<Flight>(flights);

		timetable_List.add(new Timetable("edinburgh", "london", normal));
		flights.clear();

		flights.add(new Flight("london", "edinburgh", alldays, "ba4732", "09:40", "10:50"));
		flights.add(new Flight("london", "edinburgh", alldays, "ba4752", "11:40", "12:50"));
		flights.add(new Flight("london", "edinburgh", new HashSet<String>(Arrays.asList("mo", "tu", "we", "th", "fr")), "ba4822", "18:40", "19:50"));
		
		
		normal = new LinkedList<Flight>(flights);
		//System.out.println("LISTA:" + normal);
		/*System.out.println(normal);
		Timetable a = new Timetable("london", "edinburgh", normal);
		System.out.println(a.getFlights());*/
		timetable_List.add(new Timetable("london", "edinburgh", normal));
		flights.clear();
		//System.out.println("LISTA:" + normal);

		//System.out.println(timetable_List.get(1).getFlights().size());

		flights.add(new Flight("london", "ljubljana", new HashSet<String>(Arrays.asList("fr")), "ju201", "13:20", "16:20"));
		flights.add(new Flight("london", "ljubljana", new HashSet<String>(Arrays.asList("su")), "ju213", "13:20", "16:20"));

		normal = new LinkedList<Flight>(flights);

		timetable_List.add(new Timetable("london", "ljubljana", normal));
		flights.clear();

		flights.add(new Flight("london", "zurich", alldays, "ba614", "09:10", "11:45"));
		flights.add(new Flight("london", "zurich", alldays, "sr805", "14:45", "17:20"));

		normal = new LinkedList<Flight>(flights);

		timetable_List.add(new Timetable("london", "zurich", normal));
		flights.clear();

		flights.add(new Flight("london", "milan", alldays, "ba510", "08:30", "11:20"));
		flights.add(new Flight("london", "milan", alldays, "az459", "11:00", "13:50"));

		normal = new LinkedList<Flight>(flights);

		timetable_List.add(new Timetable("london", "milan", normal));
		flights.clear();

		flights.add(new Flight("ljubljana", "zurich", new HashSet<String>(Arrays.asList("tu", "th")), "ju322", "11:30", "12:40"));
		
		normal = new LinkedList<Flight>(flights);

		timetable_List.add(new Timetable("ljubljana", "zurich", normal));
		flights.clear();

		flights.add(new Flight("ljubljana", "london", new HashSet<String>(Arrays.asList("fr")), "yu200", "11:10", "12:20"));
		flights.add(new Flight("ljubljana", "london", new HashSet<String>(Arrays.asList("su")), "yu212", "11:25", "12:20"));

		normal = new LinkedList<Flight>(flights);

		timetable_List.add(new Timetable("ljubljana", "london", normal));
		flights.clear();

		flights.add(new Flight("milan", "london", alldays, "az358", "09:10", "10:00"));
		flights.add(new Flight("milan", "london", alldays, "ba511", "12:20", "13:10"));

		normal = new LinkedList<Flight>(flights);

		timetable_List.add(new Timetable("milan", "london", normal));
		flights.clear();

		flights.add(new Flight("milan", "zurich", alldays, "sr621", "09:25", "10:15"));
		flights.add(new Flight("milan", "zurich", alldays, "sr623", "12:45", "13:35"));

		normal = new LinkedList<Flight>(flights);

		timetable_List.add(new Timetable("milan", "zurich", normal));
		flights.clear();

		flights.add(new Flight("zurich", "ljubljana", new HashSet<String>(Arrays.asList("tu", "th")), "yu323", "13:30", "14:40"));

		normal = new LinkedList<Flight>(flights);

		timetable_List.add(new Timetable("zurich", "ljubljana", normal));
		flights.clear();

		flights.add(new Flight("zurich", "london", new HashSet<String>(Arrays.asList("mo", "tu", "we", "th", "fr", "sa")), "ba613", "09:00", "9:40"));
		flights.add(new Flight("zurich", "london", new HashSet<String>(Arrays.asList("mo", "tu", "we", "th", "fr", "su")), "sr806", "16:10", "16:55"));

		normal = new LinkedList<Flight>(flights);

		timetable_List.add(new Timetable("zurich", "london", normal));
		flights.clear();

		flights.add(new Flight("zurich", "milan", alldays, "sr620", "07:55", "08:45"));

		normal = new LinkedList<Flight>(flights);

		timetable_List.add(new Timetable("zurich", "milan", normal));
		flights.clear();

		while(true){
			int option = menu(input);
			switch(option){
				case 1: check_direct_flight(input.next(), input.next(), timetable_List);
						break;
				case 2: check_available_flights_on_day(input.next(), input.next(), input.next(), timetable_List);
						break;
				case 3: check_one_flight_a_day(input.next(), input.next(), input.next(), input.next(), input.next(), timetable_List);
						break;
			}

			if(option == 4){
				break;
			}
		}
	}
}

//INTERSETAR VETORES DE VOOS
//POR TUDO EM LETRA MINUSCULA
