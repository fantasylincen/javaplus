package recharge.google;

import game.log.Logs;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import net.sf.json.JSONObject;

import recharge.utils.Base64;
import recharge.utils.Base64DecoderException;

public class GoogleVerifyPurchase {
	
	private static final String TAG = "IABUtil/Security";

    private static final String KEY_FACTORY_ALGORITHM = "RSA";
    private static final String SIGNATURE_ALGORITHM = "SHA1withRSA";
    private static final String PUBLIC_KEY[] = {
    // 简体
    "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh6M2wpGb3CNQN9dwadmO9EWOMeV0MB4MTlelcT9Y6AMu1hvqPNhC3IUNBZPerk1lkqCGHdFk+" +
    "4NMVrtu7iXl1XGpPSEZCu/PgH3ZUy7fMZbKQqvxx7R+o0sdrERQ0Dh3sANo981CJT5SsQOIE1LJ46F0idtY1fOJM0ke3ShYyt0mAEg+" +
    "FDpDGX4CJFcHv0njxYDZwvXz4Yu86gbna4jI9aQMx+QfpTN8UNPfWMeEmFg2xOtVFbCZstHvUKNAV+" +
    "mfxOkdX0ZUmwBjrWrDEYZjnrhGsbJAJaScV6WwcKFP98TtMjErxIVTTUkiALq2g09SFA3YRimobPhULOPQuPmVKwIDAQAB",
    // 繁体
    "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAk6bmz/5ghfHhhYpkpsuN4Ila8Ao3C+" +
    "KDw2oXB/PynKvqtbz5bXl3yoCVGRobGC2l33uJiG2j+s+" +
    "JhOvOmVYsfAvJh+RZE5DS7SArTbSeiyxSk3cysNxVW5VTp/XR6FvTeuw15i+BWLmEWMd7gOBMAwM0hqmcWG7cDAHr3tdm6/" +
    "McbyuIRKOtMOjzRqlNGbrMOQTlfUd82uOnCHGVp1bM3sQG9S0b46+9CQhsrgCxgDrJ27ebWlXTCllhPG9AIHGBuIM+O/RpmJ/3cen3GU53DOr2zhDrUrSJWU1PDDiV/L+" +
    "IRCqukL7jrrmIlwYfSeHacJZnFedBKenAVe3ntLnWbQIDAQAB",
    // 英文
    "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAu8AxpfJEgYiL2bZYHlOjnVQiPfz7sjIgJJt8nPThP2AD168tQEIHhHv2YPxwjaq+" +
    "hMgaeQFer6sCuKVG6Jn3ElWQyB5a0r7WAbqIKh8EMwSx8aHr/JG3wLP9ynYMhSNlrGJ0kl89HbAbnIXxXhVNwuWjTjCudaptzLN4hS3f+" +
    "/vTS08ZNgopFtiKoBfTSQcQrb4ZLqiiNAmCAOp/KDLG1IlNwypcXq0u7bL+b2UkNWJ2bUh/pVQEsdQ8HBdK6HOGw3sjOOIXhq/jnxSrEV4WSKWrqm6qYw4y9HL" +
    "/BMquUVIjTP/TkWHNuSsOTnh+bY72+YZHf2E3d8gIAshLdvNv+QIDAQAB"
    };
    
	 /**
     * Verifies that the data was signed with the given signature, and returns
     * the verified purchase. The data is in JSON format and signed
     * with a private key. The data also contains the {@link PurchaseState}
     * and product ID of the purchase.
     * @param signedData the signed JSON string (signed, not encrypted)
     * @param signature the signature for the data, signed with the private key
	 * @param versionsType 
     */
    public static boolean verifyPurchase( String signedData, String signature, byte versionsType) {
    	if( signedData.isEmpty() || signature.isEmpty() ){
    		Logs.error( "Purchase verification failed: missing data." );
    		return false;
    	}

        PublicKey key = generatePublicKey( PUBLIC_KEY[versionsType] );
        return verify(key, signedData, signature);
    }
    
    /**
     * Generates a PublicKey instance from a string containing the
     * Base64-encoded public key.
     *
     * @param encodedPublicKey Base64-encoded public key
     * @throws IllegalArgumentException if encodedPublicKey is invalid
     */
    private static PublicKey generatePublicKey(String encodedPublicKey) {
        try {
            byte[] decodedKey = Base64.decode( encodedPublicKey );
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_FACTORY_ALGORITHM);
            return keyFactory.generatePublic(new X509EncodedKeySpec(decodedKey));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            Logs.error(TAG + "Invalid key specification.");
            throw new IllegalArgumentException(e);
        } catch (Base64DecoderException e) {
           Logs.error(TAG + "Base64 decoding failed.");
            throw new IllegalArgumentException(e);
        }
    }
    
    private static boolean verify(PublicKey publicKey, String signedData, String signature) {
        Signature sig;
        try {
            sig = Signature.getInstance(SIGNATURE_ALGORITHM);
            sig.initVerify(publicKey);
            sig.update(signedData.getBytes());
            if (!sig.verify(Base64.decode(signature))) {
                Logs.error(TAG + "Signature verification failed.");
                return false;
            }
            return true;
        } catch (NoSuchAlgorithmException e) {
            Logs.error(TAG + "NoSuchAlgorithmException.");
        } catch (InvalidKeyException e) {
        	Logs.error(TAG + "Invalid key specification.");
        } catch (SignatureException e) {
        	Logs.error(TAG + "Signature exception.");
        } catch (Base64DecoderException e) {
        	Logs.error(TAG + "Base64 decoding failed.");
        }
        return false;
    }
    
    public static void main( String[] args ){
    	
    	String as	= "fzzwZeewE4mKHVH7DuDaPWRScDnLuWiS5bM9VNjBAh1IRR6TfBtZJsJRhuy40tAlJGO6R9J3HGEfLY2kfxNQyvnOo2CEiHyqbn7xDqpXFRB6xIO7JDlF+abz0CY5gpwAvOhnmFigC8AitpeSt9Q8DFkFXc0iRUvVXCjqftIf8kxgmsnNdK82mLidSOH2A9/XzL5Yzc0auTDnZkEkdMRLLf4NBntYrwXYxJKmrycSNwWnIBq0o9M7LIbiBRmEdvrb1JYql+FE0M+2CC8no8UaKyHhbTKa87h2rDamZm0iTMNnAwzBXXwmJxFyPhxUSKk6y33b/+kiyWVH1dyO3OxN9w==";
    	JSONObject jsonObject 		= new JSONObject();
		jsonObject.put("orderId", "12999763169054705758.1388834185257447" );
		jsonObject.put("packageName", "air.com.pocketriver.minilegend" );
		jsonObject.put("productId", "com.pocketriver.minilegend.1" );
		long t = 1409128926421L;
		int status = 0;
		jsonObject.put("purchaseTime", t );
		jsonObject.put("purchaseState", status );
		jsonObject.put("purchaseToken", "ecnfhhfpoeneppmmilmlodac.AO-J1OxYz0APY_Q3G8BhK9_b8HD1QV1UX4cz6pzcNQkJ_eldGxR_q5MWLJ9lycMaKpHhFp4H4lM2SXn46bNvwaosD7lnBdaUpDtXQ9v2vP_au9nQH79I2W7rlZ_8qHctK66yUL_KJUoyNUQkFrfhTMw2ABoQepPnLg" );
		
//		JSONObject json 	= JSONObject.fromObject( "{\"orderId\":\"12999763169054705758.1388834185257447\",\"packageName\":\"air.com.pocketriver.minilegend\"}" );
//		String orderId		= json.getString( "orderId" );		// 订单号
//		String productId 	= json.getString( "productId" );;	// 商品ID
		
//		System.out.println( "orderId=" + orderId + "     " + "productId=" + productId );
		
    	System.out.println( verifyPurchase( jsonObject.toString(), as, (byte) 0 ) );
    }
    
}
