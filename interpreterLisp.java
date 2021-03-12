public class interpreterLisp{

	/*private double tracker;

	private ArrayList<E> queue;
	private HashMap<E> dicFun;
	private HashMap<E> dicVar;*/

  	Stack<String> stackOne = new Stack<String>();

	String linea;
	View v= new View();

 	public String Result(String lin){
		String abc = "abcdefghijklmnopqrstuvwxyz";
		String acu="";
		int cont=0;
		linea=lin.toLowerCase();
		v.ingreso(lin);
		v.proceso(linea);
		
		for (char ch: linea.toCharArray()) {
			if (abc.indexOf(ch)>-1) {
				acu=acu+ch;
				switch(acu) {
				  case "cond":
					  v.funFound("cond");
					  acu="";
					  adentro(cont);
				    break;
				  case "quote":
					  v.funFound("quote");
					  acu="";
					  adentro(cont);
				    break;
				  case "setq":
					  v.funFound("setq");
					  acu="";
					  adentro(cont);
				    break;
				  case "defun":
					  v.funFound("defun");
					  acu="";
					  adentro(cont);
				    break;
				  case "write":
					  v.funFound("write");
					  acu="";
					  adentro(cont);
				    break;
				  default:
				    // code block
				}
			}
			
			cont++;
		}
		
		return "Hey Bestie";
	}
	
	private void adentro (int cont) {
		int parentesis=0;
		String acuparentesis="";
		for (int i=cont+1;i<linea.length();i++) {
			  if(linea.charAt(i)=='(' ) {
					parentesis++;
				}else if(linea.charAt(i)==')') {
					parentesis--;			
				}
			  if (parentesis>=0) {
					acuparentesis=acuparentesis+linea.charAt(i);
				}
		  }
		v.insideFun(acuparentesis);

	}

  

}
