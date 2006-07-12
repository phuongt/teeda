/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
if (typeof(Kumu) == 'undefined') {
    Kumu = {};
}

Kumu.VERSION = "0.1";
Kumu.NAME = "Kumu";
Kumu.DEBUG = false;
Kumu.Libs = [];

/** extends **/
Kumu.extend = function (self, obj) {
    if (self == null) {
        self = {};
    }
    for (var i = 1; i < arguments.length; i++) {
        var o = arguments[i];
        if (typeof(o) != 'undefined' && o != null) {
            for (var k in o) {
                self[k] = o[k];
            }
        }
    }
    return self;
};

Kumu = Kumu.extend(Kumu, {

    /** curry method **/
    bind : function(func){
        return function(){
            var bind = arguments;
            return function(){
                var args  = new Array();
                args = args.concat.apply(args, bind);
                args = args.concat.apply(args, arguments);
                return func.apply(null, args);
            };
        };
    },

    /** DOM shortcut method **/
    $i : function() {
        var elements = new Array();
        for (var i = 0; i < arguments.length; i++) {
            var element = arguments[i];
            if (typeof element == 'string'){
                element = document.getElementById(element);
            }
            if (arguments.length == 1) {
                return element;
            }
            elements.push(element);
        }
        return elements;
    },

    $t : function() {
        var elements = new Array();
        for (var i = 0; i < arguments.length; i++) {
            var element = arguments[i];
            if (typeof element == 'string'){
                var node = document.getElementsByTagName(element);
                for(var j = 0; j <  node.length; j++){
                    elements.push(node[j]);
                }
            }
        }
        return elements;
    },

    $n : function() {
        var elements = new Array();
        for (var i = 0; i < arguments.length; i++) {
            var element = arguments[i];
            if (typeof element == 'string'){
                var node = document.getElementsByName(element);
                for(var j = 0; j <  node.length; j++){
                    elements.push(node[j]);
                }
            }
        }
        return elements;
    },

    /** filter  map **/
    map : function(func, lst){
        if(!lst){
            lst = this;
        }
        var ret = []; 
        for(var i = 0; i < lst.length; i++){
            ret.push( func(lst[i]));
        }
        return ret;
    },

    filter : function(func, lst){
        if(!lst){
            lst = this;
        }
        var ret = [];
        for(var i = 0; i < lst.length; i++){
            var o = lst[i];
            if(func(o)){
                ret.push(o);
            }
        }
        return ret;
    },

	toArray : function(list) {
   		if (!list){
        	return [];
       	}
		if (list.toArray) {
       		return list.toArray();
        } else {
	        var arr = [];
    	    for (var i = 0; i < list.length; i++){
        		arr.push(list[i]);
        	}
        	return arr;
    	}
    },

	shift : function(list) {
	    if(!list){
	        list = this;
	    }
	    var obj = list[0];
        for (var i = 0; i < list.length-1; i++){
            list[i] = list[i + 1];
        }
	    list.length--;
	    return obj;
	},

	unshift : function() {
	    var start = 1;
	    var list;
	    if(arguments[0] instanceof Array){
	        list = arguments[0];
	    }else{
	        list = this;
	        start = 0;
	    }
	    var arr = [];
        for (var i = start; i < arguments.length; i++){
       	    if(arguments[i] instanceof Array){
       	        arr = arr.concat(arguments[i]);
       	    }else{
                arr.push(arguments[i]);
       	    }
        }
        var o = arr.concat(list);
        for (var i = 0; i < o.length; i++){
            list[i] = o[i];
        }
	    return list.length;
	},

    separate : function(str){
        var i = str.indexOf("_");
        if(i == 0){
            i = str.indexOf("_", 1);
        }
        var ar = [];
        ar.push(str.substring(0, i));
        ar.push(str.substring(i+1, str.length));
        return ar;
    }
    
    /** AOP TraceLog
    beginTrace : function(args, func){
        var funcstr = func.toString();
        var ret = funcstr.match(/[0-9A-Za-z_]+\(/).toString();
        ret = ret.substring(0,ret.length-1); 
        var str = "START function : ["+ret+"] : prameter : [";
        for(var i = 0; i < args.length; i++){
            str += args[i].toString();
            str += ' : ';
        }
        str += ']';
        try {
            var br = document.createElement("br");
            var span = document.createElement("span");
            document.body.appendChild(br);
            document.body.appendChild(span.appendChild(document.createTextNode(str)));
        } catch (e) {
        }
        return args;
    },

    endTrace : function(result, args, func){
        var funcstr = func.toString();
        var ret = funcstr.match(/[0-9A-Za-z_]+\(/).toString();
        ret = ret.substring(0,ret.length-1); 
        var str = "END function : ["+ret+"] : result : ["+result+"] : prameter : [";
        for(var i = 0; i < args.length; i++){
            str += args[i].toString();
            str += ' : ';
        }
        str += ']';
        try {
            var br = document.createElement("br");
            var span = document.createElement("span");
            document.body.appendChild(br);
            document.body.appendChild(span.appendChild(document.createTextNode(str)));
        } catch (e) {
        }
        return result;
    },
    
    addBefore : function(func, before) {
        var addfunc = function() {
            return func.apply(this, before(arguments, func));
        };
        addfunc.__proto__ = func;
        addfunc.toString = function() {
            return addfunc.__proto__.toString();
        };
        return addfunc;    
    },

    addAfter : function(func, after) {
        var addfunc = function() {
            return after(func.apply(this, arguments), arguments, func);
        };
        addfunc.__proto__ = func;
        addfunc.toString = function() {
            return addfunc.__proto__.toString();
        };
        return addfunc;    
    },

    addAround : function(func, around) {
        var addfunc = function() {
            return around(arguments, func);
        };
        addfunc.__proto__ = func;
        addfunc.toString = function() {
            return addfunc.__proto__.toString();
        };
        return addfunc;    
    },
    
    trace : function(target){
        for(var i in target){
            var obj = target[i];
            if(typeof(obj) == "function" ){
                obj = this.addBefore(obj, this.beginTrace);
                obj = this.addAfter(obj, this.endTrace);
                target[i] = obj;
            }
        }
    },
    
    debug : function(bool, obj){
        if(bool){
            if(obj){
                Kumu.trace(obj);
            }else{
                Kumu.trace(this);
            }
        }
    }*/
    
});

/** global option **/
var $i = Kumu.$i;
var $t = Kumu.$t;
var $n = Kumu.$n;

Array.prototype = Kumu.extend(Array.prototype, {
    map:Kumu.map,
    
    filter:Kumu.filter,
    
    shift:Kumu.shift,

    unshift:Kumu.unshift
    
});

Function.prototype.bind = function() {
    return Kumu.bind(this);
}

Function.prototype.bindScope = function() {
    if(arguments.length == 0){
        return this;
    }
    var x = this;
    var args = Kumu.toArray(arguments);
    var object = args.shift();
    return function() {
      var args2 = Kumu.toArray(arguments);
      args2 = args2.concat(args);        
      return x.apply(object, args2);
    }
}

Function.prototype.bindScopeAsEventListener = function() {
    if(arguments.length == 0){
        return this;
    }
    var x = this;
    var args = Kumu.toArray(arguments);
    var object = args.shift();
    return function(event) {
        var arr = Array.prototype.concat.apply([event || window.event], args);
        return x.apply(object, arr);
    }
}
