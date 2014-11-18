package org.puppet.server.bussinesslayer;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;



//yêu cầu mỗi hình chữ nhật phải ánh xạ được sang một thiết bị duy nhất
public class FindCommonEdge {
	public static int n=3;//số thiết bị
	/*//mảng chứa các đỉnh mở rộng, từ 2 mảng này lấy ra các cặp đỉnh liên tiếp chính là các cạnh liên thông giữa các hình chữ nhật		
	public  int[]addx;
	public  int[]addy;
	public int []addxx;
	public int []addyy;*/
	public static List<ArrayList<Segment>> arraylist = new ArrayList<>();
	
	public FindCommonEdge(Pool[]pool){
		int [][]x=new int[n][4];
		int [][]y=new int[n][4];
		for(int i=0;i<n;i++){
			
				//hoanh do cac dinh cua hinh chu nhat thu i
				x[i][0]=(int)pool[i].x;
				x[i][1]=(int)(pool[i].x+pool[i].width);
				x[i][2]=x[i][1];
				x[i][3]=x[i][0];
				//tung do
				y[i][0]=(int)pool[i].y;
				y[i][1]=y[i][0];
				y[i][2]=(int)(pool[i].y+pool[i].height);
				y[i][3]=y[i][2];
		}				
			
		//biến count biểu diễn đỉnh thứ count+1 đang được thêm vào đa giác
		int count=0;
		int startx;
		int starty=0;
		int endx=0;
		int endy;
		
		
		
		//kiem tra canh chung cua moi hinh chu nhat voi cac hinh chu nhat ben canh
		//dua tap hop cac canh chung cua tung hinh vao mot danh sach
		for( int i=0;i<n;i++){
			ArrayList<Segment> seglist=new ArrayList<>(); 
			for(int j=0;j<n;j++){
				
				
				
				if(j==i){
					
				}else{
					if(x[i][0]==x[j][1]){
						//x1[3]=x2[2]
						starty=(y[i][0]>y[j][1]?y[i][0]:y[j][1]);
						endy=(y[i][3]<y[j][2]?y[i][3]:y[j][2]);
						if(starty<=endy){
							/*addx[count]=x[i][0];
							addx[count+1]=x[i][0];
							addy[count]=starty;
							addy[count+1]=endy;
							count+=2;*/
							System.out.println(x[i][1]+":"+starty);
							System.out.println(x[i][1]+":"+endy);
							Point pointS=new Point(x[i][0],starty);
							Point pointE=new Point(x[i][0],endy);
							Segment segment=new Segment(pointS,pointE);
							
							seglist.add(segment);
							
							
							
						}
					}
					
					
					if(x[i][1]==x[j][0]){
						//x1[2]=x2[3]
						starty=(y[i][1]>y[j][0]?y[i][1]:y[j][0]);
						endy=(y[i][2]<y[j][3]?y[i][2]:y[j][3]);
						if(starty<=endy){					
							/*addx[count]=x[i][1];
							addx[count+1]=x[i][1];
							addy[count]=starty;
							addy[count+1]=endy;
							count+=2;	*/
							/*System.out.println(x[i][1]+":"+starty);
							System.out.println(x[i][1]+":"+endy);*/
							Point pointS=new Point(x[i][1],starty);
							Point pointE=new Point(x[i][1],endy);
							//Segment segment=new Segment(pointS,pointE);
							/*System.out.println(pointS.x);
							System.out.println(pointS.y);*/
							//arraylist[i].add(segment);
							Segment segment=new Segment(pointS,pointE);
							
							seglist.add(segment);
							
							
							}
					}
					
					
					if(y[i][0]==y[j][3]){
						//y1[1]==y2[2]
						startx=(x[i][0]>x[j][3]?x[i][0]:x[j][3]);
						endx=(x[i][1]<x[j][2]?x[i][1]:x[j][2]);
						if(startx<=endx){
							/*addxx[count]=startx;
							addxx[count+1]=endx;
							addyy[count]=y[i][0];
							addyy[count+1]=y[i][0];
							count+=2;*/
							
							Point pointS=new Point(startx,y[i][0]);
							Point pointE=new Point(endx,y[i][0]);
							Segment segment=new Segment(pointS,pointE);
							seglist.add(segment);
							
							
						}
					}
					
					if(y[i][3]==y[j][0]){
						//y1[2]==y2[1]
						startx=(x[i][3]>x[j][0]?x[i][3]:x[j][0]);
						endx=(x[i][2]<x[j][1]?x[i][2]:x[j][1]);
						if(startx<=endx){
							/*addxx[count]=startx;
							addxx[count+1]=endx;
							addyy[count]=y[i][3];
							addyy[count+1]=y[i][3];
							count+=2;
							*/
							
							Point pointS=new Point(startx,y[i][3]);
							Point pointE=new Point(endx,y[i][3]);
							Segment segment=new Segment(pointS,pointE);
							seglist.add(segment);
							
						}
					}
				}
				
			}
			arraylist.add(seglist);
		}
	}
	public static void main(String[]args){
		
		//hs=10;
		/*Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		 int H=screenSize.height;*/
		int x=0,y=0;
		Pool []pool=new Pool[n];
  	  for(int i=0;i<n;i++){    
  		 /* if(i<4){
  			   x=(float)hs;
  			   y=(float)hs+(float)H*i/4;
  		  }else{
  			  x= (float)(hs+200);
  			  y= (float)(hs+H*(i-4));
  		  } */
  		  //vi tri khoi tao mac dinh xep thanh hang ngang\
  		  if(i==0) {
  			x=0;
    		y=0;  
  		  }else if(i==1){
  			  x=pool[0].width;
  			  y=0;
  		  }else{
  			  x=pool[i-1].width+pool[i-2].width;
  			  y=0;
  		  }
  				pool[i].definePool(pool[i].width, pool[i].height, pool[i].diagonal);
  	  }
		
		
		FindCommonEdge findCE=new FindCommonEdge(pool);
		    /*System.out.println(FindCommonEdge.arraylist.size());
	        System.out.println(FindCommonEdge.arraylist.size());
	        System.out.println(FindCommonEdge.arraylist.size());*/
			for(int i=0;i<n;i++){
				System.out.println("Canh chung hinh thu "+(i+1));
				for(int j=0;j<arraylist.get(i).size();j++){
					
					Segment segment=(Segment)(arraylist.get(i).get(j));
					
					System.out.println(segment.getPointS().x+":"+segment.getPointS().y);
					System.out.println(segment.getPointE().x+":"+segment.getPointE().y);
					
				}
			}
			
		
	}
	
}



