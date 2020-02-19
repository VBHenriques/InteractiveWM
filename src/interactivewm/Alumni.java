package interactivewm;

public class Alumni {
    
private int id;
private String name;
private String degree;
private int gradyear;    

Alumni(int id, String name, String degree, int gradyear){
    this.id=id;
    this.name=name;
    this.degree=degree;
    this.gradyear=gradyear;
}

String showName(){
    return name;
}

String showDegree(){
    return degree;
}

int showGradyear(){
    return gradyear;
}

}
