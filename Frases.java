import java.util.*;
import java.lang.*;

public class Frases{
	static String show = "";
	static LinkedList<String> determinant_f = new LinkedList<String>(Arrays.asList("A", "a"));
	static LinkedList<String> determinant_m = new LinkedList<String>(Arrays.asList("O", "o"));
	static LinkedList<String> determinant_f_p = new LinkedList<String>(Arrays.asList("As", "as"));
	static LinkedList<String> determinant_m_p = new LinkedList<String>(Arrays.asList("Os", "os"));

	static LinkedList<String> verbs = new LinkedList<String>(Arrays.asList("corre","bateu", "correu"));
	static LinkedList<String> verbs_p = new LinkedList<String>(Arrays.asList("corriam","bateram", "correram"));
		
	static LinkedList<String> nouns_f = new LinkedList<String>(Arrays.asList("menina","floresta", "mae", "vida", "noticia", "cidade", "porta"));
	static LinkedList<String> nouns_m = new LinkedList<String>(Arrays.asList("tempo", "cacador", "rosto", "rio", "mar", "vento", "martelo", "cachorro", "tambor", "sino"));
	static LinkedList<String> nouns_f_p = new LinkedList<String>(Arrays.asList("lagrimas", "meninas"));
	static LinkedList<String> nouns_m_p = new LinkedList<String>(Arrays.asList("lobos", "tambores"));

	static LinkedList<String> prepositions = new LinkedList<String>(Arrays.asList("para", "com"));

	static LinkedList<String> conjuncao_f = new LinkedList<String>(Arrays.asList("pela", "na"));
	static LinkedList<String> conjuncao_m = new LinkedList<String>(Arrays.asList("pelo", "no"));

	static boolean check(String[] split){
		int gender = 0;
		
		if(!determinant_f.contains(split[0]) && !determinant_f_p.contains(split[0]) && !determinant_m.contains(split[0]) && !determinant_m_p.contains(split[0])){
			return false;
		}

		else{
			if(determinant_f.contains(split[0])){
				gender = 1;
			}

			else if(determinant_f_p.contains(split[0])){
				gender = 2;
			}

			else if(determinant_m.contains(split[0])){
				gender = 3;
			}

			else if(determinant_m_p.contains(split[0])){
				gender = 4;
			}
		}
		
		switch(gender){
			case 1: show += '"' + split[0] + '"' + " -> determinante feminino singular\n";
				break; 
			case 2:	show += '"' + split[0] + '"' + " -> determinante feminino plural\n";
				break;
			case 3: show += '"' + split[0] + '"' + " -> determinante masculino singular\n";
				break;
			case 4: show += '"' + split[0] + '"' + " -> determinante masculino plural\n";
				break;
		}
		//System.out.println(gender);


		//feminino = 1
		//f_p = 2
		//masculino = 3
		//m_p = 4

		if(!nouns_f.contains(split[1]) && !nouns_f_p.contains(split[1]) && !nouns_m.contains(split[1]) && !nouns_m_p.contains(split[1])){
			return false;
		}

		else{
			if(gender == 1 && !nouns_f.contains(split[1])){
				return false;
			}

			else if(gender == 2 && !nouns_f_p.contains(split[1])){
				return false;
			}

			else if(gender == 3 && !nouns_m.contains(split[1])){
				return false;
			}

			else if(gender == 4 && !nouns_m_p.contains(split[1])){
				return false;
			}
		}
		
		switch(gender){
			case 1: show += '"' + split[1] + '"' + " -> nome feminino singular\n";
				break; 
			case 2:	show += '"' + split[1] + '"' + " -> nome feminino plural\n";
				break;
			case 3: show += '"' + split[1] + '"' + " -> nome masculino singular\n";
				break;
			case 4: show += '"' + split[1] + '"' + " -> nome masculino plural\n";
				break;
		}

		//System.out.println("111");
		//ser singular

		if((gender == 1 || gender == 3) && !verbs.contains(split[2])){
			return false;
		}

		else if((gender == 2 || gender == 4) && !verbs_p.contains(split[2])){
			return false;
		}

		if(verbs.contains(split[2])){
			show += '"' + split[2] + '"' + " -> verbo singular\n";		
		}
	
		else{
			show += '"' + split[2] + '"' + " -> verbo plural\n";
		}

		//System.out.println("111");
		int verb_prep_deter_noun = 6;
		int verb_deter_or_contrac_noun = 5;

		if(split.length == 3){
			return true;
		}

		else if(split.length == verb_deter_or_contrac_noun){
			if(!determinant_f.contains(split[3]) && !determinant_f_p.contains(split[3]) && !determinant_m.contains(split[3]) && !determinant_m_p.contains(split[3]) && !conjuncao_m.contains(split[3]) && !conjuncao_f.contains(split[3])){
				return false;
			}

			else{
				if(determinant_f.contains(split[3]) || conjuncao_f.contains(split[3])){
					gender = 1;
				}

				else if(determinant_f_p.contains(split[3])){
					gender = 2;
				}

				else if(determinant_m.contains(split[3]) || conjuncao_m.contains(split[3])){
					gender = 3;
				}

				else if(determinant_m_p.contains(split[3])){
					gender = 4;
				}
			}

			switch(gender){
				case 1: if(determinant_f.contains(split[3])){
						show += '"' + split[3] + '"' + " -> determinante feminino singular\n";
					}
					else{
						show += '"' + split[3] + '"' + " -> conjuncao feminina\n";				
					}
					break; 
				case 2:	show += '"' + split[3] + '"' + " -> determinante feminino plural\n";
					break;
				case 3: if(determinant_f.contains(split[3])){
						show += '"' + split[3] + '"' + " -> determinante masculino singular\n";
					}
					else{
						show += '"' + split[3] + '"' + " -> conjuncao masculino\n";				
					}
					break;
				case 4: show += '"' + split[3] + '"' + " -> determinante masculino plural\n";
					break;
			}

			if(!nouns_f.contains(split[4]) && !nouns_f_p.contains(split[4]) && !nouns_m.contains(split[4]) && !nouns_m_p.contains(split[4])){
				return false;
			}

			else{
				if(gender == 1 && !nouns_f.contains(split[4])){
					return false;
				}

				else if(gender == 2 && !nouns_f_p.contains(split[4])){
					return false;
				}

				else if(gender == 3 && !nouns_m.contains(split[4])){
					return false;
				}

				else if(gender == 4 && !nouns_m_p.contains(split[4])){
					return false;
				}
			}

			switch(gender){
				case 1: show += '"' + split[4] + '"' + " -> nome feminino singular\n";
					break; 
				case 2:	show += '"' + split[4] + '"' + " -> nome feminino plural\n";
					break;
				case 3: show += '"' + split[4] + '"' + " -> nome masculino singular\n";
					break;
				case 4: show += '"' + split[4] + '"' + " -> nome masculino plural\n";
					break;
			}

			return true;

		}


		else if(split.length == verb_prep_deter_noun){
			if(!prepositions.contains(split[3])){
				return false;
			}
			
			show += '"' + split[3] + '"' + " -> preposicao\n";

			//System.out.println("111");
			if(!determinant_f.contains(split[4]) && !determinant_f_p.contains(split[4]) && !determinant_m.contains(split[4]) && !determinant_m_p.contains(split[4])){
				return false;
			}

			else{
				if(determinant_f.contains(split[4])){
					gender = 1;
				}

				else if(determinant_f_p.contains(split[4])){
					gender = 2;
				}

				else if(determinant_m.contains(split[4])){
					gender = 3;
				}

				else if(determinant_m_p.contains(split[4])){
					gender = 4;
				}
			}

			switch(gender){
				case 1: show += '"' + split[4] + '"' + " -> determinante feminino singular\n";
					break; 
				case 2:	show += '"' + split[4] + '"' + " -> determinante feminino plural\n";
					break;
				case 3: show += '"' + split[4] + '"' + " -> determinante masculino singular\n";
					break;
				case 4: show += '"' + split[4] + '"' + " ->  determinante masculino plural\n";
					break;
			}



			//System.out.println(gender);
			if(!nouns_f.contains(split[5]) && !nouns_f_p.contains(split[5]) && !nouns_m.contains(split[5]) && !nouns_m_p.contains(split[5])){
				return false;
			}

			else{
				if(gender == 1 && !nouns_f.contains(split[5])){
					return false;
				}

				else if(gender == 2 && !nouns_f_p.contains(split[5])){
					return false;
				}

				else if(gender == 3 && !nouns_m.contains(split[5])){
					return false;
				}

				else if(gender == 4 && !nouns_m_p.contains(split[5])){
					return false;
				}
			}

			switch(gender){
				case 1: show += '"' + split[5] + '"' + " -> nome feminino singular\n";
					break; 
				case 2:	show += '"' + split[5] + '"' + " -> nome feminino plural\n";
					break;
				case 3: show += '"' + split[5] + '"' + " -> nome masculino singular\n";
					break;
				case 4: show += '"' + split[5] + '"' + " -> nome masculino plural\n";
					break;
			}

			//System.out.println("111");

			return true;

		}

		else{
			System.out.println("Not in database!");
			return false;
		}

	}

	public static void main(String[] args){
		

		Scanner input = new Scanner(System.in);

		String frase = input.nextLine();
		String[] split = frase.split(" ");

		boolean var = check(split);
		
		if(var){
			System.out.println("true");		
			System.out.print(show);		
		}

		else
			System.out.println("false");

	}
}
