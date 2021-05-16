package Games.GUI.GameFrame.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.LayoutManager2;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

/* 横向布局器，和 AfXLayout 等效 
 * 
 */
public class AfRowLayout implements LayoutManager2
{
	private List<Item> items = new ArrayList<>();
	private int gap = 2;
	private boolean usePerferredSize = false; // 竖立方向是否占满
	
	public AfRowLayout()
	{		
	}
	public AfRowLayout(int gap)
	{		
		this.gap = gap; // 控件之间的间距
	}
	public AfRowLayout(int gap, boolean usePerferredSize)
	{	
		this.gap = gap;
		this.usePerferredSize = usePerferredSize;
	}
		
	@Override
	public void addLayoutComponent(String name, Component comp)
	{
		Item item = new Item();
		item.comp = comp;
		item.constraints = "auto";
		items.add(item);
	}

	@Override
	public void removeLayoutComponent(Component comp)
	{
		Iterator<Item> iter = items.iterator();
		while(iter.hasNext())
		{
			Item item = iter.next();
			if(item.comp == comp)
			{
				iter.remove();
			}
		}
	}

	@Override
	public void addLayoutComponent(Component comp, Object constraints)
	{
		Item item = new Item();
		item.comp = comp;
		item.constraints = (String) constraints;
		items.add(item);
	}
	
	@Override
	public Dimension preferredLayoutSize(Container parent)
	{
		return new Dimension(30,30);
	}

	@Override
	public Dimension minimumLayoutSize(Container parent)
	{
		return new Dimension(30,30);
	}

	@Override
	public Dimension maximumLayoutSize(Container target)
	{
		return new Dimension(30,30);
	}

	
	@Override
	public void layoutContainer(Container parent)
	{
		// 得到内矩形
		Rectangle rect = new Rectangle(parent.getWidth(), parent.getHeight());
		//Rectangle rect = parent.getBounds();
		Insets insets = parent.getInsets();
		rect.x += insets.left;
		rect.y += insets.top;
		rect.width -= (insets.left + insets.right);
		rect.height -= (insets.top + insets.bottom);
		
		// 第一轮：过滤到无效的 Item ( 有些控件是隐藏的 )
		List<Item> validItems = new ArrayList<>();
		for(Item it: items )
		{
			if(it.comp.isVisible())
				validItems.add(it);
		}
		
		// 第二轮处理：百分比，像素，auto的，直接计算出结果; 权重的，在第三轮计算
		int totalGapSize = gap * (validItems.size() - 1);// 间距大小
		int validSize = rect.width - totalGapSize;
		int totalSize = 0;
		int totalWeight = 0;
		for(Item it : validItems)
		{
			Dimension preferred = it.comp.getPreferredSize();
			it.width = preferred.width;
			it.height = usePerferredSize ? preferred.height : rect.height;
			it.weight = 0;
			
			// 计算宽度
			String cstr = it.constraints;
			if( cstr == null || cstr.length() == 0)
			{
				//System.out.println("(AfRowLayout) Warn: Must define constraints when added to container!");
			}
			else if( cstr.equals("auto"))
			{
			}
			else if(cstr.endsWith("%")) // 按百分比
			{
				int num = Integer.valueOf(cstr.substring(0,cstr.length()-1));
				it.width = validSize * num / 100;
			}
			else if(cstr.endsWith("w")) // 按权重
			{
				int num = Integer.valueOf(cstr.substring(0,cstr.length()-1));
				it.width = 0;
				it.weight = num;
			}
			else if(cstr.endsWith("px")) // 按权重
			{
				int num = Integer.valueOf(cstr.substring(0,cstr.length()-2));
				it.width = num;
			}
			else // 按像素
			{
				int num = Integer.valueOf(cstr);
				it.width = num;
			}
			
			totalSize += it.width;
			totalWeight += it.weight;
			
			//System.out.println("计算值：width=" + it.width + ",weight=" + it.weight);
		}
		
		// 第三轮: 剩余空间按权重分配
		if( totalWeight > 0)
		{
			int remainSize = validSize - totalSize;
			double unit = (double) remainSize / totalWeight;
			for(Item it : validItems)
			{
				if(it.weight > 0)
				{
					it.width = (int)( unit * it.weight );
				}
			}			
		}

		//System.out.println("总宽度: " + rect.width);
		
		// 第四轮: 按宽度和高度布局
		int x = 0;
		for(Item it : validItems)
		{
			int y = (rect.height - it.height)/2;
			if(x + it.width > rect.width)
				it.width = rect.width - x;
			if(it.width <= 0) break;
			
			it.comp.setBounds(rect.x + x, rect.y + y, it.width, it.height);
			
			//System.out.println("宽度: " + it.width);
			x += it.width;
			x += gap; // 间距
		}
	}

	@Override
	public float getLayoutAlignmentX(Container target)
	{
		return 0;
	}

	@Override
	public float getLayoutAlignmentY(Container target)
	{
		return 0;
	}

	@Override
	public void invalidateLayout(Container target)
	{
		
	}

	///////////////////////
	private static class Item
	{
		Component comp;
		String constraints = "auto";
		int width = 0;
		int height = 0;
		int weight = 0;  // 权重
	}
	

}
