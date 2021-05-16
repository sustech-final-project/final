package Games.GUI.GameFrame.layout;

/* 横向布局器，和 AfRowLayout 等效 
 * 
 */
public class AfXLayout extends AfRowLayout
{
	public AfXLayout()
	{		
	}
	public AfXLayout(int gap)
	{		
		super(gap);
	}
	public AfXLayout(int gap, boolean usePerferredSize)
	{	
		super(gap, usePerferredSize);
	}
}
