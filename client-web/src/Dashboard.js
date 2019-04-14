import React from 'react';
import Component from "./component/Component";
import Navigator from "./Navigator";
import DashboardHomeSection from "./DashboardHomeSection";
import DashBoardDataSection from "./DashBoardDataSection";
import DataValidator from "./component/utils/DataValidator";

export default class Dashboard extends Component {
    constructor(props) {
        super(props);
        this.state = {
            currentSection : 'home',
            menu : [
                {
                    active : true,
                    label : "Home",
                    name : "home"
                },
                {
                    active : false,
                    label : "To Read",
                    name : "to_read"
                }
                ],
            app_name : 'KnowTree',
        };
        this.listenOn("Nav", Dashboard.onNav, this);

    }

    render() {
        let app;
        if (this.state.currentSection === 'home') {
            app = <DashboardHomeSection/>
        } else if (this.state.currentSection === 'data') {
            app = <DashBoardDataSection/>;
        }
        return (
            <div>
                <Navigator brandName={this.state.app_name} items={this.state.menu}
                           selected={this.state.currentSection}
                />
                {app}
            </div>
        )
    }

    static onNav(context, payload) {
        DataValidator.demandString(payload);
        context.setState({currentSection : payload});
    }

}