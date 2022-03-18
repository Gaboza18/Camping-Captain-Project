<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ include file="header.jsp"%>

    <!-- 서브헤더-->
    <header class="masthead">
        <div class="container">
            <div class="masthead-subheading">캠핑족장에 오신것을 환영합니다!</div>
            <div class="masthead-heading text-uppercase">It's Nice To Meet You</div>
            <a class="btn btn-primary btn-xl text-uppercase" href="#order">예약하기</a>
        </div>
    </header>
    
    <!-- 예약하기-->
    <section class="page-section" id="order">
		<%@ include file="camping_search.jsp"%>
		<div class="clear"></div>
    </section>

    <section class="page-section bg-light" id="portfolio">
        <div class="container">
            <div class="text-center">
                <h2 class="section-heading text-uppercase">캠핑족장 지점 소개</h2>
                <h3 class="section-subheading text-muted">사진을 클릭하시면 지점 상세보기로 연결됩니다.</h3>
            </div>
            <div class="row">
                <div class="col-lg-4 col-sm-6 mb-4">
                 
                    <div class="portfolio-item">
                        <a class="portfolio-link" data-bs-toggle="modal" href="#portfolioModal1">
                            <div class="portfolio-hover">
                                <div class="portfolio-hover-content"><i class="fas fa-plus fa-3x"></i></div>
                            </div>
                            <img class="img-fluid" src="images/camping-1.jpg" alt="..." />
                        </a>
                        <div class="portfolio-caption">
                            <div class="portfolio-caption-heading">강원도지점</div>
                            <div class="portfolio-caption-subheading text-muted">강원도 동해시 촛대바위길 6</div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-sm-6 mb-4">
                    <div class="portfolio-item">
                        <a class="portfolio-link" data-bs-toggle="modal" href="#portfolioModal2">
                            <div class="portfolio-hover">
                                <div class="portfolio-hover-content"><i class="fas fa-plus fa-3x"></i></div>
                            </div>
                            <img class="img-fluid" src="images/camping-2.jpg" alt="..." />
                        </a>
                        <div class="portfolio-caption">
                            <div class="portfolio-caption-heading">경기도지점</div>
                            <div class="portfolio-caption-subheading text-muted">경기도 의왕시 월암동 543-3</div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-sm-6 mb-4">
                    <div class="portfolio-item">
                        <a class="portfolio-link" data-bs-toggle="modal" href="#portfolioModal3">
                            <div class="portfolio-hover">
                                <div class="portfolio-hover-content"><i class="fas fa-plus fa-3x"></i></div>
                            </div>
                            <img class="img-fluid" src="images/camping-3.jpg" alt="..." />
                        </a>
                        <div class="portfolio-caption">
                            <div class="portfolio-caption-heading">충청도지점</div>
                            <div class="portfolio-caption-subheading text-muted">충청북도 괴산군 불정면 세평리 </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-sm-6 mb-4 mb-lg-0">
                    <div class="portfolio-item">
                        <a class="portfolio-link" data-bs-toggle="modal" href="#portfolioModal4">
                            <div class="portfolio-hover">
                                <div class="portfolio-hover-content"><i class="fas fa-plus fa-3x"></i></div>
                            </div>
                            <img class="img-fluid" src="images/camping-4.jpg" alt="..." />
                        </a>
                        <div class="portfolio-caption">
                            <div class="portfolio-caption-heading">경상도지점</div>
                            <div class="portfolio-caption-subheading text-muted">경상북도 경주시 강동면 호명리 18번지</div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-sm-6 mb-4 mb-sm-0">
            
                    <div class="portfolio-item">
                        <a class="portfolio-link" data-bs-toggle="modal" href="#portfolioModal5">
                            <div class="portfolio-hover">
                                <div class="portfolio-hover-content"><i class="fas fa-plus fa-3x"></i></div>
                            </div>
                            <img class="img-fluid" src="images/camping-5.jpg" alt="..." />
                        </a>
                        <div class="portfolio-caption">
                            <div class="portfolio-caption-heading">전라도지점</div>
                            <div class="portfolio-caption-subheading text-muted">전라남도 무안군 현경면 해운리 910-5</div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-sm-6">
             
                    <div class="portfolio-item">
                        <a class="portfolio-link" data-bs-toggle="modal" href="#portfolioModal6">
                            <div class="portfolio-hover">
                                <div class="portfolio-hover-content"><i class="fas fa-plus fa-3x"></i></div>
                            </div>
                            <img class="img-fluid" src="images/camping-6.jpg" alt="..." />
                        </a>
                        <div class="portfolio-caption">
                            <div class="portfolio-caption-heading">제주도지점</div>
                            <div class="portfolio-caption-subheading text-muted">제주특별자치도 제주시 애월읍 소길리</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    
    <!-- 지도 -->
	<section>
		<div align="center">
   			<%@ include file="MapAddress.jsp" %>
   		</div>
	</section>

	<!-- 캠핑족장 이용 안내 -->
    <section class="page-section" id="about">
        <div class="container">
            <div class="text-center">
                <h2 class="section-heading text-uppercase">캠핑족장 이용안내</h2>
                <h3 class="section-subheading text-muted">처음 오시는 고객님은 여기를 참조해주세요 원하는 캠핑장 10분내로 예약할수 있게 도와 드리겠습니다</h3>
            </div>
            <ul class="timeline">
                <li>
                    <div class="timeline-image"><img class="rounded-circle img-fluid" src="assets/img/about/5.jpg" alt="..." /></div>
                    <div class="timeline-panel">
                        <div class="timeline-heading">
                            <h4>1. 캠핑장 예약하기</h4>
                            <h4 class="subheading">원하는 지점을 선택후 예약</h4>
                        </div>
                        <div class="timeline-body"><p class="text-muted">저희 캠핑족장의 지점들을 둘러보시고 원하는 지점을 고르셨다면 메인화면에 있는 예약하기 또는  예약하기 버튼을 눌러 지점, 체크인, 체크아웃 날짜를 선택해 주세요.<br><br><b>😭  예약서비스는 로그인이 필요해요!</b></p></div>
                    </div>
                </li>
                <li class="timeline-inverted">
                    <div class="timeline-image"><img class="rounded-circle img-fluid" src="assets/img/about/2.jpg" alt="..." /></div>
                    <div class="timeline-panel">
                        <div class="timeline-heading">
                            <h4>2. 예약후 결제 </h4>
                            <h4 class="subheading">예약을 완료했으면 결제하기</h4>
                        </div>
                        <div class="timeline-body"><p class="text-muted">예약이 완료되었으면 결제를 해주세요<br><br><b>😎 전자 결제를 이용해주세요!</b></p></div>
                    </div>
                </li>
                <li>
                    <div class="timeline-image"><img class="rounded-circle img-fluid" src="assets/img/about/3.jpg" alt="..." /></div>
                    <div class="timeline-panel">
                        <div class="timeline-heading">
                            <h4>3. 예약내역 확인하기</h4>
                            <h4 class="subheading">마이페이지 - 예약내역 조회</h4>
                        </div>
                        <div class="timeline-body"><p class="text-muted">고객님 께서 예약하신 내역을 언제든지 확인할수 있습니다<br><br><b>😄 거의 다왔습니다!</b></p></div>
                    </div>
                </li>
                <li class="timeline-inverted">
                    <div class="timeline-image"><img class="rounded-circle img-fluid" src="assets/img/about/10.jpg" alt="..." /></div>
                    <div class="timeline-panel">
                        <div class="timeline-heading">
                            <h4>4. 캠핑 즐기기</h4>
                            <h4 class="subheading">예약한 캠핑장 날짜에 맞춰 이용하기</h4>
                        </div>
                        <div class="timeline-body"><p class="text-muted">예약, 결제가 완료되었습니다 예약하신 날짜에 맞춰 캠핑족장을 이용하시면 됩니다<br><br><b>🤩 캠핑장을 이용후 후기도 남겨주세요!</b></p></div>
                    </div>
                </li>
                <li class="timeline-inverted">
                    <div class="timeline-image">
                        <h4>
                                                               캠핑족장을                              
                            <br />
                                                               이용해 주셔서
                            <br />
                                                                감사합니다!
                        </h4>
                    </div>
                </li>
            </ul>
        </div>
    </section>
    
    <!-- 지점 선택시 소개페이지 오픈 -->
    <div class="portfolio-modal modal fade" id="portfolioModal1" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="close-modal" data-bs-dismiss="modal"><img src="assets/img/close-icon.svg" alt="Close modal" /></div>
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-lg-8">
                            <div class="modal-body">
                                <h2 class="text-uppercase">캠핑족장 강원도지점</h2>
                                <p class="item-intro text-muted">강원도 동해시 촛대바위길 6</p>
                                <img class="img-fluid d-block mx-auto" src="images/camping-1.jpg" alt="..." />
                                <p>동해안 일출 명소, 추암해수욕장에서 사랑하는 가족과 연인, 친구들과 함께 즐기는 캠핑</p>
                               	<p> 동해시와 삼척시 경계 해안을 중심으로 하여 동해안의 삼해 금강이라고도 불리는 추암해변은 미묘한 해안절벽과 함께 그리움이 배인 촛대바위 그리고 크고 작은 바위섬들이 장관을 이루고 있어 해돋이 명소로 유명합니다. 
                               	       이러한 추암해변에 위치한 캠핑족장-강원도지점 에서 즐기는 캠핑은 더할 나위 없이 즐거울 것입니다.</p>
                                <ul class="list-inline">
                                    <li>
                                        <strong>Client:</strong>
                                        Threads
                                    </li>
                                    <li>
                                        <strong>지점:</strong>
                                                                       강원도 
                                    </li>
                                </ul>
                                <button class="btn btn-primary btn-xl text-uppercase" data-bs-dismiss="modal" type="button">
                                    <i class="fas fa-times me-1"></i>
                                                                닫기
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="portfolio-modal modal fade" id="portfolioModal2" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="close-modal" data-bs-dismiss="modal"><img src="assets/img/close-icon.svg" alt="Close modal" /></div>
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-lg-8">
                            <div class="modal-body">
                                <h2 class="text-uppercase">캠핑족장 경기도지점</h2>
                                <p class="item-intro text-muted">경기도 의왕시 월암동 543-3</p>
                                <img class="img-fluid d-block mx-auto" src="images/camping-2.jpg" alt="..." />
                                <p>유럽의 세련된 디자인을 바탕으로 우아한 인테리어,고급화된 기능,뛰어난 성능과 최고의 편안함을 느낄수 있는 캠핑장</p>
                                <p>유럽의 세련된 디자인을 바탕으로 우아한 인테리어, 고급화된 기능, 뛰어난 성능과 최고의 편안함을 가진 유럽형 명풍 카라반의 독특한 외관 과 실용적인 내부 인테리어를 통해 쾌적하고 편안한 캠핑을 위한 최적의 글램핑 시설 가족, 연인, 친구들과 함께 행복한 시간과 추억을 만들 수 있는 캠핑족장-경기도지점이 지금 여러분을 초대합니다.</p>
                                <ul class="list-inline">
                                    <li>
                                        <strong>Client:</strong>
                                        Explore
                                    </li>
                                    <li>
                                        <strong>지점:</strong>
                                                                       경기도
                                    </li>
                                </ul>
                                <button class="btn btn-primary btn-xl text-uppercase" data-bs-dismiss="modal" type="button">
                                    <i class="fas fa-times me-1"></i>
                                                               닫기
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="portfolio-modal modal fade" id="portfolioModal3" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="close-modal" data-bs-dismiss="modal"><img src="assets/img/close-icon.svg" alt="Close modal" /></div>
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-lg-8">
                            <div class="modal-body">
                                <h2 class="text-uppercase">캠핑족장 충청도지점</h2>
                                <p class="item-intro text-muted">충청북도 괴산군 불정면 세평리</p>
                                <img class="img-fluid d-block mx-auto" src="images/camping-3.jpg" alt="..." />
                                <p>1998년 폐교된 세평초등학교를 활용한 국악체험학습 및 캠핑장은 도민들에게 아름다운 대자연 속에서 우리전통문화에 대한 지식과 건전한 가치의 태도를 익힐수 있습니다</p>
                                <p>캠핑장내 폐교를 활용하여 아이들이 자연과 함께 놀수있고 배울수 있도록 다양한 놀이시설도 준비해 두었습니다.</p>
                                <p>캠핑장을 이용하면서 편의시설을 제공하고 있으며 캠핑장 내 매점을 운영하고 있습니다.</p>
                                <ul class="list-inline">
                                    <li>
                                        <strong>Client:</strong>
                                        Finish
                                    </li>
                                    <li>
                                        <strong>지점:</strong>
                                                                       충청도
                                    </li>
                                </ul>
                                <button class="btn btn-primary btn-xl text-uppercase" data-bs-dismiss="modal" type="button">
                                    <i class="fas fa-times me-1"></i>
                                                               닫기
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="portfolio-modal modal fade" id="portfolioModal4" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="close-modal" data-bs-dismiss="modal"><img src="assets/img/close-icon.svg" alt="Close modal" /></div>
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-lg-8">
                            <div class="modal-body">
                                <h2 class="text-uppercase">캠핑족장 경상도지점</h2>
                                <p class="item-intro text-muted">경상북도 경주시 강동면 호명리 18번지</p>
                                <img class="img-fluid d-block mx-auto" src="images/camping-4.jpg" alt="..." />
                                <p>무더운 여름 시원한 물놀이와 캠핑을 함께</p>
                                <p>자녀들과 함께 즐길수 있으며 여름에는 물놀이 시설까지 구비되어 있습니다</p>
                                <p>시원한 그늘과 함께 뜨거운 여름을 가족들고 함께 시원하게 보낼 경상도 캠핑족장으로 오세요!</p>
                                
                                <ul class="list-inline">
                                    <li>
                                        <strong>Client:</strong>
                                        Lines
                                    </li>
                                    <li>
                                        <strong>지점:</strong>
                                                                       경상도
                                    </li>
                                </ul>
                                <button class="btn btn-primary btn-xl text-uppercase" data-bs-dismiss="modal" type="button">
                                    <i class="fas fa-times me-1"></i>
                                                               닫기
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="portfolio-modal modal fade" id="portfolioModal5" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="close-modal" data-bs-dismiss="modal"><img src="assets/img/close-icon.svg" alt="Close modal" /></div>
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-lg-8">
                            <div class="modal-body">
                                <h2 class="text-uppercase">캠핑족장 전라도지점</h2>
                                <p class="item-intro text-muted">전라남도 무안군 현경면 해운리 910-5</p>
                                <img class="img-fluid d-block mx-auto" src="images/camping-5.jpg" alt="..." />
                                <p>하늘과 구름이 탁 트인 목장 같은 이색 캠핑장</p>
                                <ul class="list-inline">
                                    <li>
                                        <strong>Client:</strong>
                                        Southwest
                                    </li>
                                    <li>
                                        <strong>지점:</strong>
                                                                       전라도
                                    </li>
                                </ul>
                                <button class="btn btn-primary btn-xl text-uppercase" data-bs-dismiss="modal" type="button">
                                    <i class="fas fa-times me-1"></i>
                                  	닫기
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="portfolio-modal modal fade" id="portfolioModal6" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="close-modal" data-bs-dismiss="modal"><img src="assets/img/close-icon.svg" alt="Close modal" /></div>
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-lg-8">
                            <div class="modal-body">
                                <h2 class="text-uppercase">캠핑족장 제주도지점</h2>
                                <p class="item-intro text-muted">제주특별자치도 제주시 애월읍 소길리</p>
                                <img class="img-fluid d-block mx-auto" src="images/camping-6.jpg" alt="..." />
                                <p>파쇄석 사이트가 총 42개로 구성되어 있고, 전기, 온수, 화장실, 샤워실, 취사장, 매점, 장작판매, Wi-Fi 등의 편의시설과 운동장을 이용할 수 있습니다.
								인근에 가볼만한 관광지는 프시케월드, 한라산 백록담, 제주 항파두리 항몽 유적이 있고, 주변에 다른 캠핑장으로 제주 꼼지락 캠핑장, 제주캠핑클럽, 공짜로자는집, 제주몽 홀리데이파크, 잼핑홀리데이 애월점이 있습니다.</p>
                                <ul class="list-inline">
                                    <li>
                                        <strong>Client:</strong>
                                        Window
                                    </li>
                                    <li>
                                        <strong>지점:</strong>
                                                                       제주도
                                    </li>
                                </ul>
                                <button class="btn btn-primary btn-xl text-uppercase" data-bs-dismiss="modal" type="button">
                                    <i class="fas fa-times me-1"></i>
                                                                 닫기
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script src="js/scripts.js"></script>
    <!-- SB Forms JS -->
    <!-- Activate your form at https://startbootstrap.com/solution/contact-forms -->
    <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>

<%@ include file="footer.jsp"%>