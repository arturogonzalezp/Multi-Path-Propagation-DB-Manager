package mx.arturogonzalezp.mppdb.twitter;

import java.util.List;
import mx.arturogonzalezp.mppdb.structures.MPPItemInterface;
import mx.arturogonzalezp.mppdb.structures.MPPLoaderInterface;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class MPPTwitterLoader implements MPPLoaderInterface{
	private Twitter twitter;
	private ConfigurationBuilder cb;
	private TwitterFactory factory;
	private String source;
	public static final int LOAD_FROM_URL = 1;
	public static final int LOAD_FROM_DISK = 2;
	public boolean isLoaded;
	public List<MPPItemInterface> nodeList;
	private int loadMethod;	
	public MPPTwitterLoader(){
		this.setTwitter(null);
		this.setCb(new ConfigurationBuilder());
		this.getCb().setDebugEnabled(true);
		this.setFactory(null);
		this.setLoadMethod(-1);
	}
	public MPPTwitterLoader(String oAuthConsumerKey, String oAuthConsumerSecret, String oAuthAccessToken, String oAuthAccessTokenSecret){
		this();
		this.getCb().setOAuthConsumerKey(oAuthConsumerKey);
		this.getCb().setOAuthConsumerSecret(oAuthConsumerSecret);
		this.getCb().setOAuthAccessToken(oAuthAccessToken);
		this.getCb().setOAuthAccessTokenSecret(oAuthAccessTokenSecret);
		this.setFactory(new TwitterFactory(this.getCb().build()));
		this.setTwitter(this.getFactory().getInstance());
	}
	public Twitter getTwitter() {
		return twitter;
	}
	public void setTwitter(Twitter twitter) {
		this.twitter = twitter;
	}
	public ConfigurationBuilder getCb() {
		return cb;
	}
	public void setCb(ConfigurationBuilder cb) {
		this.cb = cb;
	}
	public TwitterFactory getFactory() {
		return factory;
	}
	public void setFactory(TwitterFactory factory) {
		this.factory = factory;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public int getLoadMethod() {
		return loadMethod;
	}
	public void setLoadMethod(int loadMethod) {
		this.loadMethod = loadMethod;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
