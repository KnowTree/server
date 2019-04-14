import React from 'react';
import Component from "./component/Component";
import DataValidator from "./component/utils/DataValidator";
import {CommonFields} from "./component/utils/CommonFields";

export default class Navigator extends Component {
    constructor(props) {
        super(props);
        this.state = {};
        this.setItems();
        this.itemSelected = this.itemSelected.bind(this);
        this.onAddClick = this.onAddClick.bind(this);
    }

    setItems() {
        let items = [];
        if (this.props.items) {
            for (let item of this.props.items) {
                DataValidator.demandObject(item);
                items.push(item);
            }
        }
    }

    getItems() {
        return this.props ? this.props.items : [];
    }

    createNavItems() {
        let items = this.getItems();
        let children = [];
        for (let item of items) {
            if (item.name === this.props.selected) {
                children.push(<li className="nav-item active">
                    <a className="nav-link">{item.label}</a>
                </li>)
            } else {
                children.push(<li className="nav-item">
                    <a onClick={this.itemSelected(item.name)} className="nav-link">{item.label}</a>
                </li>)
            }
        }
        let parent = [];
        parent.push(<div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav">{children}</ul></div>);
        return parent;
    }

    render() {
        return (
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <a className="navbar-brand" href="#">{this.props.brandName}</a>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                {this.createNavItems()}
                <button className="btn btn-primary" onClick={this.onAddClick}>Add</button>
            </nav>
        )
    }

    itemSelected(name) {
        return (e) => this.fireEvent("Nav", name);
    }

    onAddClick() {
        this.fireEvent("Add");
    }
}