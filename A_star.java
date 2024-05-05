class solveAstar{
  int g=0;
  public void print(int puzzle[]){
    for(int i=0;i<9;i++){
      if(i%3==0)System.out.println();
      if(puzzle[i]==-1) System.out.print("_ ");
      else System.out.print(puzzle[i]+" ");  
    }
    System.out.println( );
  }
  public void solve(int start[],int goal[]){
      moveTile(start, goal);
      print(start);
      int h=heuristic(start,goal);
      if(h==0){
        return;
      }
      solve(start,goal);
  }
  public void moveTile(int start[],int goal[]){
     g++;
     int emptyAt=0;
     for(int i=0;i<9;i++){
      if(start[i]==-1){
        emptyAt=i;
        break;
      }
     }
     int t1[]= new int[9];
     int t2[]= new int[9];
     int t3[]= new int[9];
     int t4[]= new int[9];
     int f1=Integer.MAX_VALUE,f2=Integer.MAX_VALUE,f3=Integer.MAX_VALUE,f4=Integer.MAX_VALUE;
     copy(t1,start);
     copy(t2,start);
     copy(t3,start);
     copy(t4,start);
  
     int col=emptyAt%3;
     int row=emptyAt/3;
     if(col-1>=0){
       moveLeft(t1,emptyAt);
       f1=heuristic(t1, goal);
     }
     if(col+1<3){
       moveRight(t2,emptyAt);
       f2=heuristic(t2, goal);
     }
     if(row-1>=0){
       moveUP(t3,emptyAt);
       f3=heuristic(t3, goal);
     }
     if(row+1<3){
       moveDown(t4,emptyAt);
       f4=heuristic(t4, goal);
     }
      


      if(f1<=f2 && f1<=f3 && f1<=f4) moveLeft(start, emptyAt); 
      else if(f2<=f1 && f2<=f3 && f2<=f4) moveRight(start, emptyAt);
      else if(f3<=f1 && f3<=f2 && f3<=f4) moveUP(start, emptyAt);
      else moveDown(start, emptyAt);

      
  }
  public int heuristic(int start[],int goal[]){
      int h=0;
      for(int i=0;i<9;i++){
        if(start[i]!=goal[i] && start[i]!=-1) h++;
      }
      return h;
  }
  public void copy(int temp[],int start[]){
    for(int i=0;i<9;i++) temp[i]=start[i];
  }
  public void moveLeft(int start[],int pos){
    swap(start,pos,pos-1);
  }
  public void moveRight(int start[],int pos){
    swap(start,pos,pos+1);
  }
  public void moveUP(int start[],int pos){
    swap(start,pos,pos-3);
  }
  public void moveDown(int start[],int pos){
    swap(start,pos,pos+3);
  }
  public void swap(int start[],int idx1,int idx2){
    int x=start[idx1];
    start[idx1]=start[idx2];
    start[idx2]=x;
  }
}
class A_star{
  public static void main(String[] args) {
    int start[]={1,2,3,-1,4,6,7,5,8};
    int goal[]={1,2,3,4,5,6,7,8,-1};
    solveAstar obj = new solveAstar();
    System.out.println("Start State :-");
    obj.print(start);
    System.out.println("Goal State :-");
    obj.print(goal);
    System.out.println("********************");
    obj.solve(start, goal);
    System.out.println("No move is equal to "+obj.g);
    
  }
}