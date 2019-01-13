import React from 'react';
import Validator from 'validate.js';
import DataValidator from "./utils/DataValidator";
import EventListener from "../EventListener";

export default class Component extends React.Component {
    constructor(props) {
        super(props);
    }

    getProperty(name) {
        return this.props[name];
    }

    fireEvent(name, payload) {
        EventListener.getInstance().fireEvent(name, payload);
    }

    listenOn(name, func, context) {
        EventListener.getInstance().register(name, func, context);
    }

}