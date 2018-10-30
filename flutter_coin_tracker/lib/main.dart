import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

void main() => runApp(new MyApp());

class MyApp extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => MyHomePage();

}

class MyHomePage extends State<MyApp> {

  var list; //List result after get from API will hold here
  var refresKey = GlobalKey<RefreshIndicatorState>();

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'COIN TRACKER',
      theme: ThemeData.light(),
      home: Scaffold(
        appBar: AppBar(title: Text('COIN TRACKER')),
        body: Center(
          child: RefreshIndicator(
              child: null,
              onRefresh: null),
        ),
      )
    );
  }

  @override
  void initState(){
    super.initState();
    
    refreshListCoin();
  }



  Future<Null> refreshListCoin(){
    refresKey.currentState?.show(atTop: false);

    setState(() async{
      list = await fetchListCoin(); //Assign data to list
    });

    return null;
  }

}

Future<List<CoinMarket>> fetchListCoin() async{

  final api_endpoint = await http.get('https://api.coinmarketcap.com/v1/ticker/');

  if(api_endpoint.statusCode == 200){
    List coins = json.decode(api_endpoint.body);

    return coins.map((coin) => new CoinMarket.fromJson(coin)).toList();

  } else{
    throw Exception('Failed to load Coin List');
  }

}

class CoinMarket{

  final String id;
  final String name;
  final String symbol;
  final String price_usd;
  final String percent_change_1h;
  final String percent_change_24h;
  final String percent_change_7h;

  CoinMarket({this.id, this.name, this.price_usd, this.symbol, this.percent_change_1h,
    this.percent_change_24h, this.percent_change_7h});

  factory CoinMarket.fromJson(Map<String, dynamic> json){
    return CoinMarket(
      id:json["id"],
      name:json["name"],
      symbol:json["symbol"],
      price_usd:json["price_usd"],
      percent_change_1h:json["percent_change_1h"],
      percent_change_24h:json["percent_change_24h"],
      percent_change_7h:json["percent_change_7h"]
    );
  }


}