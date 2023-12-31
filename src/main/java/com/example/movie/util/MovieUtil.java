package com.example.movie.util;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.example.movie.entity.Movie;
import com.example.movie.exception.GetMovieException;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovieUtil {
	String moviesUrl = "https://m.maoyan.com/ajax/movieOnInfoList";
	String infoUrl = "https://m.maoyan.com/ajax/detailmovie";
	private static final CloseableHttpClient client = HttpClients.createMinimal();
	Map<String,String> headers = new HashMap<>();
	public MovieUtil(){
		headers.put("User-Agent", "PostmanRuntime/7.28.4");
		headers.put("Accept","*/*");
		headers.put("Connection","keep-alive");
	}

	public List<Movie>  getHotMovie() throws GetMovieException {

		List<Movie> movies = new ArrayList<>();
		List<Integer> movieIds;
		try{
			HttpEntity entity = sendGet(moviesUrl,-1);
			JSONArray jsonArray = JSON.parseObject(entity.getContent()).
					getJSONArray("movieIds");
			movieIds = jsonArray.toJavaList(Integer.class);
			for (Integer id : movieIds){
				entity = sendGet(infoUrl,id);
				if (entity == null || entity.getContentLength() == -1){
					continue;
				}

				Movie movie = JSON.parseObject(entity.getContent()).getObject("detailMovie",Movie.class);
if(movie.releasedTime == null) {
	try {
		movie.setReleasedTime(new java.sql.Date(parseTime(movie.pubDesc).getTime()));
	} catch (ParseException e) {
		System.out.println(e.getMessage());
	}
}
				movies.add(movie);
				entity.getContent().close();
				System.out.println(movie);
			}
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
			throw new GetMovieException(e.getMessage());
		}

		return movies;
	}

	private HttpEntity sendGet(String url, Integer MovieId) throws URISyntaxException, IOException {
		URIBuilder uriBuilder = new URIBuilder(url);
		if (MovieId != -1){
			uriBuilder.addParameter("movieId",MovieId.toString());
		}

		HttpGet httpGet = new HttpGet(uriBuilder.build());
		RequestConfig requestConfig = RequestConfig.custom()
				.setCookieSpec(CookieSpecs.STANDARD)
				.setConnectTimeout(500)
				.setSocketTimeout(1000)
				.setConnectionRequestTimeout(1000)
				.build();
		httpGet.setConfig(requestConfig);

		for (Map.Entry<String,String> entry : headers.entrySet()){
			httpGet.setHeader(entry.getKey(),entry.getValue());
		}

		return client.execute(httpGet).getEntity();
	}

	private Date parseTime(String pubDesc) throws ParseException {
		Scanner in = new Scanner(pubDesc);
		String charList = in.nextLine();
		in.close();

		int i = 0;
		Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher matcher = pattern.matcher(charList);

		charList = matcher.replaceAll("");

		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		date = format.parse(charList);
		return date;
	}

	public static void main(String ...args) throws GetMovieException {
		MovieUtil movieUtil = new MovieUtil();
		movieUtil.getHotMovie();
	}
}