package com.hexaware.simplyfly.service;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
	
	private static final String SECRET = "HnEDCKYobu5um1XAsbqn4rkC+DWXzT4Sfx2B+NT5Q437fvgg4rVjFoMLht0bFR2E1AfcvqEIJ4KrsqIxLSCcQgZ16qOlT6Io7E5tsqYXHaRs8DtjFRWOBsMAU4CJctSzRiXKEEDTx9Z86/j/BecaMG4CQw+6K3+RKkITRzIUVhATOrM5rZOpIGYWH7/f9WHxvmCbDBdqVz0bW7+tg7ggGd1ysDL7BrJ84Rj+qxMgabCukXP+77NkKCuifw7i1WknGTSmixHPFL46hs3XoJ7M/p9b0IkqfSRmBfnA9pDyqeDF00Rom+xlsiUtT/tC7UD0tHi2HeztEeTVbmu35HXvLZ9RoyztXy7uUdq1IS2N6ao=\r\n";

	public String generateToken(String username,Collection<? extends GrantedAuthority> authorities) {
		
		Map<String,Object> claims=new HashMap<>();
		String role = authorities.iterator().next().getAuthority();
		claims.put("role", role);
		return createToken(claims,username);
		
	}

	private String createToken(Map<String, Object> claims, String username) {
		return Jwts.builder().
				setClaims(claims).setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
				.claim("role", claims.get("role"))
				.signWith(getSignKey(),SignatureAlgorithm.HS256).compact();
	
	}

	private Key getSignKey() {
		byte[] keyBytes=Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	
	 public String extractUsername(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }

	    public Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }

	    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimsResolver.apply(claims);
	    }

	    private Claims extractAllClaims(String token) {
	        return Jwts
	                .parserBuilder()
	                .setSigningKey(getSignKey())
	                .build()
	                .parseClaimsJws(token)
	                .getBody();
	    }

	    private Boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }

	    public Boolean validateToken(String token, UserDetails userDetails) {
	        final String username = extractUsername(token);
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }


}
