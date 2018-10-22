package com.ethjava;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.utils.Numeric;

public class WalletGen {
	
	public static void GenWallet() {
		try {
			ECKeyPair ecKeyPair = Keys.createEcKeyPair();
			String privateKey = Numeric.toHexStringWithPrefix(ecKeyPair.getPrivateKey());
			String publicKey = Numeric.toHexStringWithPrefix(ecKeyPair.getPublicKey());
			String address = Keys.toChecksumAddress(Credentials.create(ecKeyPair).getAddress());
			System.out.println(privateKey);
			System.out.println(publicKey);
			System.out.println(address);
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
