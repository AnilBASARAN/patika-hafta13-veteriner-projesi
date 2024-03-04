# Veteriner Yönetim Sistemi



Veteriner Yönetim Sistemi, bir veteriner kliniğinin işlerini yönetmesine yardımcı olan bir RESTful API'dir. Bu API, veteriner doktorları, müşteriler, hayvanlar, aşılar ve randevular dahil olmak üzere çeşitli kaynakları yönetmek için endpoint'ler sağlar.




### Başlıca Özellikler

- Veteriner doktorları kaydetme, güncelleme, görüntüleme ve silme
- Doktorların müsait günlerini kaydetme, güncelleme, görüntüleme ve silme
- Müşterileri kaydetme, güncelleme, görüntüleme ve silme
- Müşterilere ait hayvanları kaydetme, güncelleme, görüntüleme ve silme
- Hayvanlara uygulanan aşıları kaydetme, güncelleme, görüntüleme ve silme
- Hayvanlar için veteriner hekimlere randevu oluşturma, güncelleme, görüntüleme ve silme

### Proje Ekran Görüntüleri

*Entity Relationship Diagram*

![veteriner-rest-api](/image/schema1.png)




### API Endpoint'leri

### Veteriner Doktorları Yönetme

- `POST http://localhost:8080/v1/doctors`: Yeni bir doktor oluşturur.
- `GET http://localhost:8080/v1/doctors/1`: Belirli bir ID'ye sahip doktoru alır.
- `GET http://localhost:8080/v1/doctors?page=0&size=3`: Sayfalama ve sıralama yaparak doktorları alır.
- `PUT http://localhost:8080/v1/doctors`: Bir doktoru günceller.
- `DELETE http://localhost:8080/v1/doctors/1`: Belirli bir ID'ye sahip doktoru siler.

### Doktorların Müsait Günlerini Yönetme

- `POST http://localhost:8080/v1/available-dates`: Yeni bir müsait tarih oluşturur.
- `GET http://localhost:8080/v1/available-dates/1`: Belirli bir ID'ye sahip müsait tarihi alır.
- `GET http://localhost:8080/v1/available-dates?page=0&size=3`: Sayfalama ve sıralama yaparak müsait tarihleri alır.
- `PUT http://localhost:8080/v1/available-dates`: Bir müsait tarihi günceller.
- `DELETE http://localhost:8080/v1/available-dates/1`: Belirli bir ID'ye sahip müsait tarihi siler.

### Müşterileri Yönetme

- `POST http://localhost:8080/v1/customers`: Yeni bir müşteri oluşturur.
- `GET http://localhost:8080/v1/customers/1`: Belirli bir ID'ye sahip müşteriyi alır.
- `GET http://localhost:8080/v1/customers?page=0&size=3`: Sayfalama ve sıralama yaparak müşterileri alır.
- `GET http://localhost:8080/v1/customers/1/animals`: Belirli bir ID'ye sahip müşterinin hayvanlarını alır.
- `GET http://localhost:8080/v1/customers/filterByName?name=ahmet`: Müşterileri isimlerine göre filtreler.
- `PUT http://localhost:8080/v1/customers`: Bir müşteriyi günceller.
- `DELETE http://localhost:8080/v1/customers/1`: Belirli bir ID'ye sahip müşteriyi siler.

### Müşterilere Ait Hayvanları Yönetme

- `POST http://localhost:8080/v1/animals`: Yeni bir hayvan oluşturur.
- `GET http://localhost:8080/v1/animals/1`: Belirli bir ID'ye sahip hayvanı alır.
- `GET http://localhost:8080/v1/animals?page=0&size=3`: Sayfalama ve sıralama yaparak hayvanları alır.
- `GET http://localhost:8080/v1/animals/1/customer`: Belirli bir ID'ye sahip hayvanın sahibini alır.
- `GET http://localhost:8080/v1/animals/filter?name=Peluş`: Hayvanları isimlerine göre filtreler.
- `GET http://localhost:8080/v1/animals/vaccines?startDate=2022-01-01&endDate=2024-12-31`: Tarih aralığına göre hayvanlara uygulanan aşıları alır.
- `PUT http://localhost:8080/v1/animals/1`: Belirli bir ID'ye sahip hayvanı günceller.
- `DELETE http://localhost:8080/v1/animals/1`: Belirli bir ID'ye sahip hayvanı siler.

### Hayvanlara Uygulanan Aşıları Yönetme

- `POST http://localhost:8080/v1/vaccines`: Yeni bir aşı oluşturur.
- `GET http://localhost:8080/v1/vaccines/1`: Belirli bir ID'ye sahip aşıyı alır.
- `GET http://localhost:8080/v1/vaccines?page=0&size=3`: Sayfalama ve sıralama yaparak aşıları alır.
- `GET http://localhost:8080/v1/vaccines/animal/1 `: Belirli bir ID'ye sahip hayvana uygulanan aşıları alır.
- `GET http://localhost:8080/v1/vaccines/protection-dates?start_date=2023-12-31&end_date=2024-12-31`: Girilen tarih aralığına göre aşı koruma tarihlerini alır.
- `PUT http://localhost:8080/v1/vaccines`: Bir aşıyı günceller.
- `DELETE http://localhost:8080/v1/vaccines/1`: Belirli bir ID'ye sahip aşıyı siler.
- `PUT http://localhost:8080/v1/vaccines/vaccinate`: Bir hayvana aşı uygular.

### Hayvanlar İçin Veteriner Hekimlere Randevu Oluşturma

- `POST http://localhost:8080/v1/appointments/create`: Bir hayvana randevu oluşturur.
- `GET http://localhost:8080/v1/appointments/1`: Belirli bir ID'ye sahip randevuyu alır.
- `GET http://localhost:8080/v1/appointments?page=0&size=3`: Sayfalama ve sıralama yaparak randevuları alır.
- `GET http://localhost:8080/v1/appointments/doctor/1?start_date_time=2023-01-01T10:00:00&end_date_time=2023-05-01T11:00:00`: Belirli bir ID'ye sahip doktorun belirli bir tarih aralığındaki randevularını alır.
- `GET http://localhost:8080/v1/appointments/animal/2?start_date_time=2023-01-01T10:00:00&end_date_time=2023-02-01T11:00:00`: Belirli bir ID'ye sahip hayvanın belirli bir tarih aralığındaki randevularını alır.
- `PUT http://localhost:8080/v1/appointments`: Bir randevuyu günceller.
- `DELETE http://localhost:8080/v1/appointments/1`: Belirli bir ID'ye sahip randevuyu siler.

### Veritabanı Yapısı

Veritabanı altı ana varlık içerir: `Doctor`, `AvailableDate`, `Customer`, `Animal`, `Vaccine`, ve `Appointment`.

- `Doktor`: Klinikteki veterinerleri temsil eder. Her doktorun bir `UygunTarih` ve `Randevu` listesi bulunur.
- `UygunTarih`: Bir doktorun uygun tarihlerini temsil eder. Her uygun tarih bir `Doktor` ile ilişkilidir.
- `Müşteri`: Kliniğin müşterilerini temsil eder. Her müşterinin bir `Hayvan` listesi bulunur.
- `Hayvan`: Müşterilere ait hayvanları temsil eder. Her hayvan bir `Müşteri` ile ilişkilidir ve bir `Aşı` ve `Randevu` listesine sahiptir.
- `Aşı`: Hayvanlara uygulanan aşıları temsil eder. Her aşı bir `Hayvan` ile ilişkilidir.
- `Randevu`: Hayvanların bir doktora görünmesi için yapılan randevuları temsil eder. Her randevu bir `Doktor` ve bir `Hayvan` ile ilişkilidir.



