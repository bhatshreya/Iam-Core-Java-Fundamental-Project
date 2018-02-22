package epita.fr.fundamental.project;

public class User_Login {
	
	private String DispName;
	private String uid;
	private String email;
	
	public String getDispName(){
		return DispName;
	}
	
	public void setDispName(String DispName){
		this.DispName=DispName;
	}
	
	public String getUid(){
		return uid;
	}
	
	public void setUid(String uid){
		this.uid=uid;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email=email;
	}
	
	public void display()
	{
		System.out.println("Name="+DispName+"ID"+uid+"Email"+email);
	}

}


