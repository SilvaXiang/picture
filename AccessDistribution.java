package util.access.distribution;

import org.dom4j.Element;

import util.xml.Histogram;
import util.xml.HistogramUtils;


public interface AccessDistribution {
	
	public double getData();
	
	
	
	public int getDataInteger();
	
	
	public static AccessDistribution getAccessDistribution(int type, int begin, int end, int skew) {
		
		if(begin >= end)
			throw new RuntimeException("begin must be less than end, begin < end!");
		
		switch(type) {
		case 0:return new UniformAccessDistribution(begin, end);
		case 1:return new NormalAccessDistribution(begin, end);
		case 2:return new ZipfAccessDistribution(begin, end, skew);
		}
		return null;
	}
	
	
	public static AccessDistribution getAccessDistributionByXML(Element accessDistributionXML, int begin,int end) {
		Histogram distributionHistogram = 
				HistogramUtils.initHistogram((Element)accessDistributionXML.selectSingleNode("access_distribution"));
		int skew = Integer.parseInt((accessDistributionXML.selectSingleNode("access_distribution/skew").getText()));
		return getAccessDistribution(distributionHistogram.getRandomValue(), begin, end, skew);
	}
}
