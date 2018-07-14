package com.dantask03.count;


import com.hazelcast.jet.Jet;
import com.hazelcast.jet.JetInstance;
import com.hazelcast.jet.pipeline.Pipeline;
import com.hazelcast.jet.pipeline.Sinks;
import com.hazelcast.jet.pipeline.Sources;

import java.util.List;
import java.util.Map;

import static com.hazelcast.jet.Traversers.traverseArray;
import static com.hazelcast.jet.aggregate.AggregateOperations.counting;
import static com.hazelcast.jet.function.DistributedFunctions.wholeItem;

public class JetCounter {


//        vytvoril DAG + job, ktery spocita pocet slov v tom JSONu a v tom XMLku


    public static void countWords(String string) {

        // Model distributed computation job
        // Analogy of interconnected waterpipes
        // Contains 'Stages', these accept streams from prev stages
        //   and send it on to next stages
        Pipeline p = Pipeline.create();

        // Stage can be obtained directly 'drawFrom()
        p.drawFrom(Sources.<String>list("text"))
                // FlatMapping - making words out of lines, split by nonword characters
                .flatMap(word -> traverseArray(word.toLowerCase().split("\\W+")))
                // Filtering out empty words
                .filter(word -> !word.isEmpty())
                // Counting step
                .groupingKey(wholeItem()).aggregate(counting())
                // Drain results to some datastructure (IMDG map)
                .drainTo(Sinks.map("counts"));

        JetInstance jet = Jet.newJetInstance();
        try {
            List<String> text = jet.getList("text");
            text.add(string);
            // Performing computation, job execution
            jet.newJob(p).join();

            // Fetch the map
            Map<String, Long> counts = jet.getMap("counts");
            for (String word : counts.keySet()) {
                System.out.println(word + ": " + "" + counts.get(word));
            }

        } finally {
            Jet.shutdownAll();
        }
    }
}
