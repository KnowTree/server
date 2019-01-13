export default class EventListener {
    static getInstance() {
        if (!EventListener.instance) EventListener.instance = new EventListener();
        return EventListener.instance;
    }

    constructor() {
        this.maps = {};
    }

    register(eventName, func, context) {
       if (!this.maps[eventName]) this.maps[eventName] = [];
       this.maps[eventName].push({
           func : func,
           context : context
       });
    }

    fireEvent(name, payload) {
        if (!this.maps[name]) console.warn("No event " + name + " registered");
        else {
            for (let cf of this.maps[name]) {
                cf.func(cf.context, payload);
            }
        }
    }
}