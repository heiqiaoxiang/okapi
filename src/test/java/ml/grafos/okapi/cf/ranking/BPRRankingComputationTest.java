package ml.grafos.okapi.cf.ranking;

import java.util.LinkedList;
import java.util.List;

import ml.grafos.okapi.cf.CfLongIdFloatTextInputFormat;

import org.apache.giraph.conf.GiraphConfiguration;
import org.apache.giraph.io.formats.IdWithValueTextOutputFormat;
import org.apache.giraph.utils.InternalVertexRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BPRRankingComputationTest {
	BPRRankingComputation bpr;
	
	@Before
	public void setUp() throws Exception {
		bpr = new BPRRankingComputation();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFull() throws Exception{
		String[] graph = { 
				"1 1 1",
				"2 1 1",
				"3 2 1",
				"4 1 1",
				"4 2 1",
				"5 3 1",
		};

		GiraphConfiguration conf = new GiraphConfiguration();
		conf.setComputationClass(BPRRankingComputation.class);
		conf.setEdgeInputFormatClass(CfLongIdFloatTextInputFormat.class);
		conf.set("minItemId", "1");
		conf.set("maxItemId", "3");
		conf.set("iter", "1");
		conf.setVertexOutputFormatClass(IdWithValueTextOutputFormat.class);
		Iterable<String> results = InternalVertexRunner.run(conf, null, graph);
		List<String> res = new LinkedList<String>();
		for (String string : results) {
			res.add(string);
			//System.out.println(string);
		}
		//Assert.assertEquals(8, res.size());
	}
}
