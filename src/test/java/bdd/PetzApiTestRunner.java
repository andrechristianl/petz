package bdd;

import com.intuit.karate.KarateOptions;
import com.petz.code.core.bdd.ApiTestRunner;

@KarateOptions(features = "classpath:../../assets/bdd/features/keyStore.feature")
public class PetzApiTestRunner extends ApiTestRunner {
}