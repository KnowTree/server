import React from "react";
import ReactDOM from "react-dom";
import style from './Style.css';
const Index = () => {
    return <div className={style.a}>Hello React Blue!</div>;
};

ReactDOM.render(<Index />, document.getElementById("index"));