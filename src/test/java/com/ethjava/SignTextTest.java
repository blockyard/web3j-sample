package com.ethjava;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Sign;
import org.web3j.crypto.Sign.SignatureData;
import org.web3j.utils.Numeric;

public class SignTextTest {
	
	private final Logger log = LoggerFactory.getLogger(SignTextTest.class);
	
	@Test
	public void test() {
		String rawMsg = "hello world";
		String privateKey = "bf936ecb5f42e3ba0219fec9caad47b5967c3112d7505e4d347c0cc711136445";
		
		// While the following line of code dose not work:
		//ECKeyPair ecKey = ECKeyPair.create(props.getPrivateKey().getBytes());
		// So we need to create a Credential object first.
		Credentials cred = Credentials.create(privateKey);
		ECKeyPair ecKey = cred.getEcKeyPair();
		
		SignatureData signedData = Sign.signMessage(rawMsg.getBytes(), ecKey);
		
		byte[] r = signedData.getR();
		Assert.assertEquals(32, r.length);
		byte[] s = signedData.getS();
		Assert.assertEquals(32, s.length);
		byte v = signedData.getV();
		byte[] combined = new byte[r.length + s.length + 1];
		int i = 0;
		for (; i < combined.length; ++i) {
			if (i < 32) {
				combined[i] = r[i];
			} else if (i >= 32 && i < 64) {
				combined[i] = s[i-32];
			} else {
				combined[i] = v;
			}
		}
		
		log.info(String.format("the signed hash is %s", Numeric.toHexString(combined)));
	}
}
