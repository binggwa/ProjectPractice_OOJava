package module3;

import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import parsing.ParseFeed;
import processing.core.PApplet;

/**
 * OO Java Programming 데이터 구조와 그 너머 강의 : Java의 객체 지향 프로그래밍
 * Module 3 지도 마커 예시
 *
 * @author 이병관
 * @since 2025.04.03
 */
public class EarthquakeCityMapPractice extends PApplet {
	/**
	 * UnfoldingMap 형식의 private 변수 map 선언
	 * earthquakeURL에서 지진정보를 가져오기 위한 earthquakesURL 변수
	 */
	private UnfoldingMap map;
	private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";
	/**
	 * PApplet 상속에 따른 2가지 메서드 구현 setup, draw
	 */
	public void setup() {
		size(950, 600, OPENGL); // 캔버스 사이즈 설정
		// UnfoldingMap 형식 개체 구축, 캔버스에서 맵의 좌표를 this,200x50으로 지정, 700x500 크기로 구축
		map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider()); 
		map.zoomToLevel(2); // 사용 중인 맵의 확대/축소 레벨을 지정
		MapUtils.createDefaultEventDispatcher(this, map); // 이벤트 발송자는 지도를 더블클릭하거나, 줌레벨을 변경, 다른지역으로 확대 등이 가능
		
		Location valLoc = new Location(-38.14f, -73.03f); // 마커를 표시할 위도경도 설정
		// SimplePointMarker val = new SimplePointMarker(valLoc); // SimplePointMarker 타입의 val 선언, 마커는 한 장소를 원으로 표시한다.
		// map.addMarker(val); // val에 대한 마커 추가
		Feature valEq = new PointFeature(valLoc); // 속성을 지정할 수 있는 valEq
		valEq.addProperty("title", "Valdivia, Chile"); // add를 통해 String key와 Object value로 속성의 이름과 값을 추가한다.
		valEq.addProperty("magnitude", "9.5");
		valEq.addProperty("date", "May 22, 1960");
		valEq.addProperty("year", "1960");
		
		Marker valMk = new SimplePointMarker(valLoc, valEq.getProperties());
		map.addMarker(valMk);
		
		// List<PointFeature> bigEqs = new ArrayList<PointFeature>(); // 지진 각각에 대한 기능 객체를 만들 수 있다. 제네릭을 통해 어떤 유형의 객체가 저장되어있는지 지정
		// bigEqs.add(valEq);
		// bigEqs.add(alaskaEq);
		// bigEqs.add(sumatraEq);
		// bigEqs.add(japanEq);
		// bigEqs.add(kamchatkaEq);
		// 지진이 발생할 때마다 객체와 속성을 추가하는 것은 너무 비효율적임
		// ParseFeed를 이용해 RSS feed에서 얻어온 최신 정보에 상응하는 마커를 지도에 추가 가능
		List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
		
		List<Marker> markers = new ArrayList<Marker>();
		for (PointFeature eq: earthquakes) {
			markers.add(new SimplePointMarker(eq.getLocation(), eq.getProperties()));
		}
		map.addMarkers(markers);
	}
	/**
	 * PApplet 상속에 따른 2가지 메서드 구현 setup, draw
	 */
	public void draw() {
		background(10); // 캔버스 배경색 설정
		map.draw(); // 설정한 대로 map을 그리는 메서드
		//addKey(); // 지진의 강도를 나타내는 표식을 해석, 디자인할 수 있는 메서드
	}
}
