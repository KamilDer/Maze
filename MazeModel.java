




class MazeModel {
	
	private final double segmentSizeX=2;
	private final double segmentSizeY=3;
	private final double segmentOpeningSize=1;
	
	private final double segmentBorderWidth=0.025;
	private final double ballRadius=0.05;
	
	



	private double segmentType=1;
	private double x=0.3;
	private double y=0.3;
	private double vx=0.0;
	private double vy=0.0;
	private double ax=0.70710678;
	private double ay=0.70710678;
	private final int angleBase=45;
	private int angleMultiplier=1;
	
	private boolean collisionDetectedX=false;
	private boolean collisionDetectedY=false;
	private int collisionCount=0;
	
	private double timeInSegment=0;
	
	
	public double getTimeInSegment() {
		return timeInSegment;
	}
	public boolean isCollisionDetectedX() {
		return collisionDetectedX;
	}
	public boolean isCollisionDetectedY() {
		return collisionDetectedY;
	}
	public int getCollisionCount() {
		return collisionCount;
	}
	public double getAX() {
		return ax;
	}

	public double getAY() {
		return ay;
	}
	
	
	
	public void setAAngleMultiplier45(int multiplier){
		
		//angleMultiplier=multiplier modulo 8
		this.angleMultiplier=multiplier < 0 ? 8 + multiplier : multiplier % 8;
		
		//angle=multiplier*angleBase
		//ax=cos(angle)
		//ay=sin(angle)
		
		
		switch (this.angleMultiplier) {
        case 0:  ax=1;ay=0;
                 break;
        case 1:  ax=0.70710678;ay=0.70710678;
                 break;
        case 2:  ax=0;ay=1;
                 break;
        case 3:  ax=-0.70710678;ay=0.70710678;
                 break;
        case 4:  ax=-1;ay=0;
                 break;
        case 5:  ax=-0.70710678;ay=-0.70710678;
                 break;
        case 6:  ax=0;ay=-1;
                 break;
        case 7:  ax=0.70710678;ay=-0.70710678;
                 break;
        default: ax=0;ay=0;
                 break;
    }
		
		
	}
	
	
	public int getAngleMultiplier() {
		return angleMultiplier;
	}
	public int getAngle() {
		return angleMultiplier*angleBase;
	}
	public double getVX() {
		return vx;
	}
	public double getVY() {
		return vy;
	}
	public MazeModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public double getSegmentSizeX() {
		return segmentSizeX;
	}
	public double getSegmentSizeY() {
		return segmentSizeY;
	}
	public double getSegmentOpeningSize() {
		return segmentOpeningSize;
	}
	public double getSegmentType() {
		return segmentType;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	
	public double getSegmentBorderWidth() {
		return segmentBorderWidth;
	}
	
	public double getBallRadius() {
		return ballRadius;
	}
	
	void update(double seconds){
		seconds=seconds>0?seconds:0;
		
		timeInSegment=timeInSegment+seconds;
		
		//
		
		x=x+seconds*vx+seconds*seconds*ax/2;
		y=y+seconds*vy+seconds*seconds*ay/2;
		
		vx=vx+seconds*ax;
		vy=vy+seconds*ay;
		
		
		//collision
		collisionDetectedX=false;
		if(
			(x<(segmentBorderWidth+ballRadius))  && (y>(segmentOpeningSize-ballRadius))
			){
			collisionDetectedX=true;
			collisionCount++;
			
			x=(segmentBorderWidth+ballRadius);
			vx=0;
		}
		else 
		if(
			((x>(segmentSizeX-(segmentBorderWidth+ballRadius)))&&(y<(segmentSizeY-(segmentOpeningSize-ballRadius))))
			){
			collisionDetectedX=true;
			collisionCount++;
			
			x=(segmentSizeX-(segmentBorderWidth+ballRadius));
			vx=0;
		}

		
		collisionDetectedY=false;
		if(y<(0+ballRadius)){
			collisionDetectedY=true;
			collisionCount++;
			
			y=(0+ballRadius);
			vy=0;
		}
		else 
		if(y>(segmentSizeY-ballRadius)){
			collisionDetectedY=true;
			collisionCount++;
			
			y=(segmentSizeY-ballRadius);
			vy=0;
		}
		
		
		
		// changing segment
		if( x>=segmentSizeX){
			
			timeInSegment=0;
			
			segmentType=segmentType*(-1);
			
			x=x-segmentSizeX;
			y=segmentSizeY-y;
			
			//vx=vx;
			vy=-vy;
			
			
		}
		
		if( x<0){
			
			timeInSegment=0;
			
			segmentType=segmentType*(-1);
			
			x=-(x-segmentSizeX);
			y=segmentSizeY-y;
			
			//vx=vx;
			vy=-vy;
			
			
		}
	
		
	}

}
