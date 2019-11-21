package bdd;

import com.intuit.karate.KarateOptions;
import com.itau.latam.core.bdd.ApiTestRunner;

@KarateOptions(features = "classpath:../../assets/bdd/features/keystore.feature")
public class KeyStoreApiTestRunner extends ApiTestRunner {
}